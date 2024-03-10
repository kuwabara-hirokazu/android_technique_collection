package com.example.android_technique_collection.feature.searchphoto.state

enum class PagingState {
    /** ページングしていない状態 */
    NONE,

    /** ページング中 */
    PAGING,

    /** ページングエラー */
    ERROR
}
