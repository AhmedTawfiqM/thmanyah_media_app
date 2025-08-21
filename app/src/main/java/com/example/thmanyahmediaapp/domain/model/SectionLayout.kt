package com.example.thmanyahmediaapp.domain.model

import com.google.gson.annotations.SerializedName

enum class SectionLayout {
    @SerializedName("square")
    SQUARE,

    @SerializedName("2_lines_grid")
    TWO_LINES_GRID,

    @SerializedName("big_square")
    BIG_SQUARE,

    /** this type is a Workaround to handle issue from Api response */
    @SerializedName("big square")
    BIGSQUARE,

    @SerializedName("queue")
    QUEUE
}