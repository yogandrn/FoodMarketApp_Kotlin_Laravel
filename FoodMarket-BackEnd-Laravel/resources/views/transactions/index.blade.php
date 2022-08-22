<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('Transactions') }}
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white">
                <table class="table-auto w-full">
                    <thead>
                    <tr>
                        <th class="border px-6 py-4">#</th>
                        <th class="border px-6 py-4">Order ID</th>
                        <th class="border px-6 py-4">User</th>
                        <th class="border px-6 py-4">Date & Time</th>
                        <th class="border px-6 py-4">Total</th>
                        <th class="border px-6 py-4">Status</th>
                        <th class="border px-6 py-4">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                        @forelse($transactions as $item)
                        
                            <tr>
                                <td class="border px-6 py-4">{{ $loop->iteration }}</td>
                                <td class="border px-6 py-4">{{ $item->code }}</td>
                                <td class="border px-6 py-4 ">{{ $item->user->name }}</td>
                                <td class="border px-6 py-4">{{$item->date }}</td>
                                <td class="border px-6 py-4">Rp {{ number_format($item->total) }}</td>
                                <td class="border px-6 py-4">{{ $item->status }}</td>
                                {{-- <td class="border px-6 py-4"><img src="{{ $item->picturePath }}" width="120px"></td> --}}
                                <td class="border px-6 py-4 text-center" >
                                    <a href="{{ route('transactions.show', $item->id) }}" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mx-2 rounded" >
                                        See
                                    </a>
                                    <form action="{{ route('transactions.destroy', $item->id) }}" method="POST" class="inline-block">
                                        {!! method_field('delete') . csrf_field() !!}
                                        <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 mx-2 rounded inline-block" >
                                     <a onclick="return confirm('Apakah Anda yakin ingin menghapus data ini?');">Delete</a>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        @empty
                            <tr>
                               <td colspan="7" class="border text-center p-5">
                                   Data Tidak Ditemukan
                               </td>
                            </tr>
                        @endforelse
                    </tbody>
                </table>
            </div>
            <div class="text-center mt-5">
                {{ $transactions->links() }}
            </div>
        </div>
    </div>
</x-app-layout>
