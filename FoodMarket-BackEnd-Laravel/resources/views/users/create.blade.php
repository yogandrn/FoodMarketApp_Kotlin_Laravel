<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {!! __('User &rsaquo; Create') !!}
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

                <form action="{{ route('users.store') }}" method="post" class="w-full" enctype="multipart/form-data">
                    @csrf

                    {{-- Form Nama  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="name" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Name</label>
                        <input type="text" value="{{ old('name') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="name" name="name" placeholder="Type your name" required>
                    </div>

                    {{-- Form Email  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="email" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Email Address</label>
                        <input type="email" name="email" value="{{ old('email') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="email"  placeholder="Type your email address" required>
                    </div>

                    {{-- Form phone number  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="phoneNumber" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Phone Number</label>
                        <input type="tel" name="phoneNumber" maxlength="15" value="{{ old('phoneNumber') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="phoneNumber"  placeholder="Type your phone number" required>
                    </div>

                    {{-- Form address  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="address" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Address</label>
                        <input type="text" name="address" value="{{ old('address') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="address"  placeholder="Type your address" required>
                    </div>

                    {{-- Form house number  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="houseNumber" class="block tracking-wide text-grey-700 text-s font-bold mb-3">House Number</label>
                        <input type="text" name="houseNumber" value="{{ old('houseNumber') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="houseNumber"  placeholder="Type your house number" required>
                    </div>

                    {{-- Form City  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="city" class="block tracking-wide text-grey-700 text-s font-bold mb-3">City</label>
                        <input type="text" name="city" value="{{ old('city') }}" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="city"  placeholder="Type your city" required>
                    </div>

                    {{-- Form Password  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="password" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Password</label>
                        <input type="password" name="password"  class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="password"  placeholder="Type your password" required>
                    </div>

                    {{-- Form Confirm password  --}}
                    <div class="flex flex-wrap -mx-3 mb-6 ">
                        <label for="password_confirmation" class="block tracking-wide text-grey-700 text-s font-bold mb-3">Confirm Password</label>
                        <input type="password" name="password_confirmation"  class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="password_confirmation"  placeholder="Type your password again" required>
                    </div>

                    {{-- Pilih Roles  --}}
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full">
                            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="roles">
                                Roles
                            </label>
                            <select name="roles" class="appearance-none block w-full bg-grey text-grey-700 border border-grey-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="roles">
                                <option value="USER">User</option>
                                <option value="ADMIN">Admin</option>
                            </select>
                        </div>
                    </div>

                    {{-- Upload photo  --}}
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full">
                            <label class="block tracking-wide text-gray-700 text-s font-bold mb-2" for="profile_photo">
                                Profile Photo
                            </label>
                            <input name="profile_photo_path" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="profile_photo" type="file" placeholder="Choose your profile photo" required>
                        </div>
                    </div>

                    {{-- Tombol Simpan --}}
                    <div class="flex flex-wrap -mx-3 mb-6">
                        <div class="w-full px-3 text-right">
                            <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                                Save User
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</x-app-layout>
