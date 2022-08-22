<?php

namespace App\Models;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Transaction extends Model
{
    use HasFactory, SoftDeletes;

    protected $fillable = ['code', 'date', 'food_id', 'user_id', 'quantity', 'total', 'status', 'payment_url'];

    public function food() {
        return $this->hasOne(Food::class, 'id', 'food_id');
    }

    public function user() {
        return $this->belongsTo(User::class);
    }

    // public function getCreatedAtAttribute($value) {
    //     // return Carbon::parse($value)->timestamp;
    //     return Carbon::parse($value)->format('Y-m-d h:i:s');
    // }
    
    // public function getDateAttribute($value) {
    //     // return Carbon::parse($value)->timestamp;
    //     return Carbon::parse($value)->format('Y M d');
    // }

    // public function getUpdatedAtAttribute($value) {
    //     return Carbon::parse($value)->timestamp;
    // }
    
    protected function serializeDate($date)
{
    return $date->format('Y-m-d H:i:s');
}
}
