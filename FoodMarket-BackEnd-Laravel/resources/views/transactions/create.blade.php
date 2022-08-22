<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {!! __('Food &rsaquo; Create') !!}
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8" >
            <div >
                @if ($errors->any())
                <div  role="alert" style="margin-bottom: 1.5rem;">
                    <div class="bg-red-500 text-white font-bold text-l rounded-t px-4 py-2" >
                        There's something wrong
                    </div>
                    <div class="border border-t-0 border-red-400 rounded-b px-4 py-3 text-red-700 bg-red-100">
                        @foreach($errors->all() as $error)
                            <li>{{ $error}}</li>
                        @endforeach
                    </div>
                </div>
                @endif

                <form action="{{ route('food.store') }}" method="post" class="w-full" enctype="multipart/form-data">
                    @csrf

                    {{-- Form Nama  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="name" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Name</label>
                        <input type="text" value="{{ old('name') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="name" name="name" placeholder="Type the food name" required>
                    </div>

                    {{-- Form Deskripsi  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="description" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Description</label>
                        <input type="text" name="description" value="{{ old('description') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="description"  placeholder="Type the description " required>
                    </div>

                    {{-- Form ingredient  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="ingredient" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Ingredients</label>
                        <input type="text" name="ingredient" max-length="15" value="{{ old('ingredient') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="ingredient"  placeholder="Type the ingredients" required>
                        <p class="text-gray-600 text-xs text-italic">Pisahkan dengan tanda koma ",". Contoh: Ayam, Sayur, dll.</p>
                    </div>

                    {{-- Pilih Category  --}}
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full">
                            <label class="block tracking-wide  text-s font-bold mb-2" for="type">
                                Category
                            </label>
                            <select name="type" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="type">
                                <option value="RECOMMENDED">Recommended</option>
                                <option value="POPULAR">Populer</option>
                                <option value="NEW_FOOD">Menu Baru</option>
                            </select>
                        </div>
                    </div>

                    {{-- Form price  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="price" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Price</label>
                        <input type="number" name="price" value="{{ old('price') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="price"  placeholder="Type the food price" required min="1" max="9999999">
                    </div>

                    {{-- Form rate  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="rate" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Rate</label>
                        <input type="number" name="rate" value="{{ old('rate') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="rate" step="0.01" max="5" placeholder="Type the food rate" required>
                    </div>

                    {{-- Upload photo  --}}
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full">
                            <label class="block tracking-wide text-s font-bold mb-2" for="picturePath">
                                Food Picture
                            </label>
                            <input name="picturePath" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="picturePath" type="file" placeholder="Choose your profile photo" required>
                        </div>
                    </div>

                    {{-- Tombol Simpan --}}
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full px-3 text-right">
                            <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded " >
                                Submit
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</x-app-layout>
