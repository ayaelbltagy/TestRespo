package com.example.productsdemoapp.models

data class DataModel(
    val `data`: List<Data>,
    val error: Any,
    val isSuccessed: Boolean
) {
    data class Data(
        val approved_by_admin: Int,
        val avg_rate: Int,
        val category: Category,
        val category_id: Int,
        val count_users_review: Int,
        val created_at: String,
        val description: String,
        val description_ar: String,
        val id: Int,
        val images: List<Image>,
        val is_active: Int,
        val main_image: Any,
        val prepare_time: Int,
        val prices: List<Price>,
        val provider: Provider,
        val provider_id: Int,
        val review: List<Any>,
        val title: String,
        val title_ar: String,
        val updated_at: String
    ) {
        data class Category(
            val created_at: String,
            val description: String,
            val description_ar: String,
            val id: Int,
            val logo: String,
            val published: Int,
            val title: String,
            val title_ar: String,
            val updated_at: String
        )

        data class Image(
            val admin_id: Int,
            val created_at: String,
            val id: Int,
            val image_name: String,
            val imageable_id: Int,
            val imageable_type: String,
            val title: String,
            val updated_at: String
        )

        data class Price(
            val created_at: String,
            val id: Int,
            val is_active: Int,
            val offer_end_date: Any,
            val offer_price: Any,
            val price: Int,
            val product_id: Int,
            val title: String,
            val title_ar: String,
            val unit: Any,
            val unit_id: Any,
            val updated_at: String
        )

        data class Provider(
            val account_number: String,
            val bank_name: String,
            val categories: List<Category>,
            val confirm_code: Any,
            val confirm_expiration: Any,
            val country: String,
            val country_code: String,
            val created_at: String,
            val deposits_count: Int,
            val email: String,
            val fees_percentage: Any,
            val gov: String,
            val highKMPrice: Int,
            val iban: String,
            val id: Int,
            val images: List<Image>,
            val lat: Double,
            val lng: Double,
            val lowKMPrice: Int,
            val lowest_value: Any,
            val mobile: String,
            val name: String,
            val rate: Int,
            val reset_expiration: Any,
            val reset_status: Int,
            val slogan: String,
            val status: Int,
            val wallet: String,
            val zone: String
        ) {
            data class Category(
                val created_at: String,
                val description: String,
                val description_ar: String,
                val id: Int,
                val logo: String,
                val published: Int,
                val title: String,
                val title_ar: String,
                val updated_at: String
            )

            data class Image(
                val admin_id: Int,
                val created_at: String,
                val id: Int,
                val image_name: String,
                val imageable_id: Int,
                val imageable_type: String,
                val title: String,
                val updated_at: String
            )
        }
    }
}