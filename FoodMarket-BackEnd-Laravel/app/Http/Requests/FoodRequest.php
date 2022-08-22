<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class FoodRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'name' => ['required', 'string', 'max:255'],
            'description' => ['required', 'string'],
            'ingredient' => ['required', 'string', 'max:255'],
            'type' => ['required', 'string', 'max:255'],
            'price' => ['required', 'integer'],
            'rate' => ['required', 'numeric'],
            'picturePath' => [ 'image'],
        ];
    }
}
