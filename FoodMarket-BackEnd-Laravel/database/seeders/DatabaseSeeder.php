<?php

namespace Database\Seeders;

use App\Models\Food;
use App\Models\User;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\Hash;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        // \App\Models\User::factory(10)->create();

        User::create([
            'name' => 'Yoga Andrian',
            'email' => 'yoga@gmail.com',
            'password' => Hash::make('password'),
            'address' => 'Jln. Mastrip',
            'houseNumber' => '29A',
            'phoneNumber' => '081590466534',
            'city' => 'Jember',
            'roles' => 'ADMIN'
        ]);

        Food::create([
            'name' => 'Ayam Geprek',
            'description' => 'Lorem ipsum',
            'ingredient' => 'Ayam, Sayur, Sambal',
            'type' => 'Makanan Pedas',
            'price' => 25000,
            'rate' => 4.6,
        ]);
    }
}
