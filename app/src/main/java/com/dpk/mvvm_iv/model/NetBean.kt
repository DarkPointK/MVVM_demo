package com.dpk.mvvm_iv.model

class NetBean {

data class PostInspection(
    val jsonrpc: String,
    val method: String,
    val params: InspectionParams
)

data class InspectionParams(
    val name: String,
    val b64: String
)

data class GetInspection(
    val info: String,
    val lpn: String,
    val img: String
)
}