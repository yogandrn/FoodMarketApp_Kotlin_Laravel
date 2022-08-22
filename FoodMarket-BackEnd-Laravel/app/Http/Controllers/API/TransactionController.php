<?php

namespace App\Http\Controllers\API;

use App\Helpers\ResponseFormatter;
use App\Http\Controllers\Controller;
use App\Models\Transaction;
use Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Midtrans\Config;

class TransactionController extends Controller
{
    public function all(Request $request)
    {
        $id = $request->input('id');
        $limit = $request->input('limit', 6);
        $food_id = $request->input('food_id');
        $status = $request->input('status');

        if ($id) {
            $transaction = Transaction::with(['food', 'user'])->find($id);

            if ($transaction) {
                return ResponseFormatter::success($transaction, 'Data transaksi berhasil diambil', 200);
            } else {
                return ResponseFormatter::error(null, 'Data transaksi tidak tersedia', 200);
            }
        }

        $transaction = Transaction::with(['food', 'user'])->where('user_id', Auth::user()->id)->orderBy('created_at', 'desc');

        if ($food_id) {
            $transaction->where('food_id', $food_id);
        }

        if ($status) {
            $transaction->where('status', $status);
        }

        return ResponseFormatter::success($transaction->get(), 'Data list transaksi berhasil diambil', 200);
    }
    
    public function detail($id)
    {
        try {
            $transaction = Transaction::where('id', $id)->with(['food', 'user',])->first();
            return ResponseFormatter::success($transaction, "Transaksi berhasil diambil", 200);
        } catch(Exception $e) {
            return ResponseFormatter::error(null, $e->getMessage(), 200);
        }
    }

    public function update(Request $request, $id)
    {
        $transaction = Transaction::findOrFail($id);

        $transaction->update($request->all());

        return ResponseFormatter::success($transaction, 'Transaksi berhasil diperbarui', 200);
    }

    public function checkout(Request $request)
    {
        $request->validate([
            'user_id' => 'required|exists:users,id',
            'food_id' => 'required|exists:food,id',
            'quantity' => 'required',
            'total' => 'required',
            'status' => 'required'
        ]);
        $code = date('Ymd') . rand(100,999);
        $transaction = Transaction::create([
            'code' => $code,
            'date' => date('Y-m-d H:i:s'),
            'user_id' => $request->user_id,
            'food_id' => $request->food_id,
            'quantity' => $request->quantity,
            'total' => $request->total,
            'status' => $request->status,
            'payment_url' => "",
        ]);

        //konfigurasi midtrans
        Config::$serverKey = config('services.midtrans.serverKey');
        Config::$isProduction = config('services.midtrans.isProduction');
        Config::$isSanitized = config('services.midtrans.isSanitized');
        Config::$is3ds = config('services.midtrans.is3ds');

        // panggil transaksi yang dibuat
        $transaction =Transaction::with(['food', 'user'])->find($transaction->id);

        // membuat transaksi midtrans
        $midtrans = [
            'transaction_details' => [
                'order_id' => $transaction->code,
                'gross_amount' => (int)$transaction->total,
            ],
            'customer_detail' => [
                'first_name' => $transaction->user->name,
                'email' => $transaction->user->email,
            ],
            'enabled_payments' => ['gopay', 'bank_transfer', 'indomaret'],
            'vtweb' => []
        ];

        // memanggil midtrans
        try {
            // ambil halaman payment midtrans
            $paymentUrl = \Midtrans\Snap::createTransaction($midtrans)->redirect_url;
            $transaction->payment_url = $paymentUrl;
            $transaction->save();

            // mengembalikan data ke API
            return ResponseFormatter::success($transaction, 'Transaksi berhasil', 200);
        } catch (Exception $e) {
            return ResponseFormatter::error($e->getMessage(), 'Transaksi gagal', 200);

        }
    }
    
    public function confirm($id) {
        try {
            Transaction::where('id', $id)->update(['status' => 'SUCCESS']);
            return ResponseFormatter::success(null, 'Order Berhasil Dikonfirmasi', 200);
        } catch (Exception $e) {
            return ResponseFormatter::error($e->getMessage(), 'Something went wrong!', 200);
        }
    }
}
