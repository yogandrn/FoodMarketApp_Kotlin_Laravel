<?php

use App\Http\Controllers\API\FoodController;
use App\Http\Controllers\API\MidtransController;
use App\Http\Controllers\API\TransactionController;
use App\Http\Controllers\API\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->group(function() {
    Route::get('/user', [UserController::class, 'fetch']); // ambil data user
    Route::post('/user/update', [UserController::class, 'updateProfile']); //update data user
    Route::post('/user/photo', [UserController::class, 'updatePhoto']); // update photo profile
    Route::post('/logout', [UserController::class, 'logout']); //logout

    Route::post('/checkout', [TransactionController::class, 'checkout']);

    Route::get('/transactions', [TransactionController::class, 'all']); // ambil list transaksi user
    Route::get('/transaction/{id}', [TransactionController::class, 'detail']);
    Route::post('/transaction/{id}', [TransactionController::class, 'update']); // update data transaksi
});

Route::post('/login', [UserController::class, 'login']);
Route::post('/register', [UserController::class, 'register']);

Route::get('/food', [FoodController::class, 'all']);
Route::get('/test', function() {return 'Hello world!';});

Route::post('/midtrans/callback', [MidtransController::class, 'callback']);
Route::get('/midtrans/callback', function() {
    return "Hello World!";
});

Route::post('/transaction/{id}/confirm', [TransactionController::class, 'confirm']);
