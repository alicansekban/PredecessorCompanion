package com.alican.predecessorcompanion.custom.tab

fun getTabList(): List<TabItemModel> {
    return arrayListOf(
        TabItemModel(
            "Players",
            "players"
        ),
        TabItemModel(
            "Builds",
            "builds"
        ),
    )
}

data class TabItemModel(
    val title: String,
    val id: String
)
