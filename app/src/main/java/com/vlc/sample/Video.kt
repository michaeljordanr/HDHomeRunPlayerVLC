package com.vlc.sample

import com.google.gson.annotations.SerializedName;

data class Video(
    @SerializedName("AudioCodec")
    val audioCodec: String,
    @SerializedName("GuideName")
    val guideName: String,
    @SerializedName("GuideNumber")
    val guideNumber: String,
    @SerializedName("HD")
    val hD: Int,
    @SerializedName("URL")
    val uRL: String,
    @SerializedName("VideoCodec")
    val videoCodec: String
)