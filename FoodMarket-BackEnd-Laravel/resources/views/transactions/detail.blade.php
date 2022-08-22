<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            Transaction &rsaquo; {{ $transaction->food->name }} by {{$transaction->user->name}}
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="w-full rounded overflow-hidden shadow-lg px-6 py-6 bg-white">
                <div class=" -mx-4 -mb-4 md:mb-0 " style="display:flex;flex-direction:row; justify-content:space-between">
                    <div class="w-full md:w-1/6 px-4 mb-4 md:mb-0" style="width: 16%;">
                        <img src="/foodmarket/laravel/storage/app/public/{{ $transaction->food->picturePath }}" alt="{{$transaction->food->name}}" class="w-full rounded">
                    </div>
                    <div class="w-full md:w-5/6 px-4 mb-4 md:mb-0" style="width: 84%;">
                        <div class="flex flex-wrap mb-3" style="display:flex;flex-direction:row; justify-content:space-between">
                            <div class="w-2/6" style="width: 33.33333333%;">
                                <div class="text-sm">Product Name</div>
                                <div class="text-xl font-bold">{{ $transaction->food->name }}</div>
                            </div>
                            <div class="w-1/6" style="width: 16.66667%;">
                                <div class="text-sm">Quantity</div>
                                <div class="text-xl font-bold">{{ number_format($transaction->quantity) }}</div>
                            </div>
                            <div class="w-2/6" style="width: 33.33333333%;">
                                <div class="text-sm">Total</div>
                                <div class="text-xl font-bold">Rp {{ number_format($transaction->total) }}</div>
                            </div>
                            <div class="w-1/6" style="width: 16.66667%;">
                                <div class="text-sm">Status</div>
                                <div class="text-xl font-bold">{{ $transaction->status }}</div>
                            </div>
                        </div>
                        <div class="flex flex-wrap mb-4" style="display:flex;flex-direction:row; justify-content:space-between">
                            <div class="w-2/6" style="width: 33.33333333%;">
                                <div class="text-sm">User Name</div>
                                <div class="text-xl font-bold">{{ $transaction->user->name }}</div>
                                <div class="text-md font-bold">{{ $transaction->user->email }}</div>
                            </div>
                            <div class="w-3/6" style="width: 50%;">
                                <div class="text-sm">Address</div>
                                <div class="text-xl font-bold">{{ $transaction->user->address . " Nomor ". $transaction->user->houseNumber . ", " . $transaction->user->city }}</div>
                            </div>
                            <div class="w-1/6" style="width: 16.66667%;">
                                <div class="text-sm">Phone Number</div>
                                <div class="text-xl font-bold">{{ $transaction->user->phoneNumber }}</div>
                            </div>
                        </div>
                        
                        <div class="flex flex-wrap mb-2" style="display:flex;flex-direction:row; justify-content:space-between">
                            <div class="w-5/6" style="width: 83.3333333%;">
                                <div class="text-sm">Payment URL</div>
                                <div class="text-lg">
                                    <a href="{{ $transaction->payment_url }}" target="blank">{{ $transaction->payment_url }}</a>
                                </div>
                            </div>
                            <div class="w-1/6" style="width: 16.66667%;">
                            @if ($transaction->status == "ON_PROCESS") 
                                <div class="text-sm">Change Status</div>
                                <a href="{{ route('transactions.changeStatus', ['id' => $transaction->id, 'status' => 'ON_DELIVERY']) }}"
                                   class="bg-blue-500 hover:bg-blue-700 text-white font-bold px-2 rounded block text-center w-full mb-2 px-3 py-1"
                                   onclick="return confirm('Ganti status transaksi menjadi ON_DELIVERY?');" >
                                    On Delivery
                                </a>
                                <!--<a href="{{ route('transactions.changeStatus', ['id' => $transaction->id, 'status' => 'DELIVERED']) }}"-->
                                <!--   class="bg-green-500 hover:bg-green-700 text-white font-bold px-2 rounded block text-center w-full mb-2 px-3 py-1"-->
                                <!--   onclick="return confirm('Ganti status transaksi ke DELIVERED?');">-->
                                <!--    Delivered-->
                                <!--</a>-->
                                <!--<a href="{{ route('transactions.changeStatus', ['id' => $transaction->id, 'status' => 'CANCELLED']) }}"-->
                                <!--   class="bg-red-500 hover:bg-red-700 text-white font-bold px-2 rounded block text-center w-full mb-2 px-3 py-1"-->
                                <!--   onclick="return confirm('Ganti status transaksi menjadi CANCELLED?');">-->
                                <!--    Cancelled-->
                                <!--</a>-->
                                @else <div></div>
                                @endif
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</x-app-layout>
