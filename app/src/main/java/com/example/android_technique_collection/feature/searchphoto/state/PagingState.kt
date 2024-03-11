package com.example.android_technique_collection.feature.searchphoto.state

enum class PagingState {
    /** 全てのデータが読み込まれ、これ以上ページングするデータがない状態 */
    FULL,

    /** ページング可能、新しいデータをロードできる状態 */
    READY,

    /** ページング処理が進行中の状態 */
    LOADING,

    /** データの読み込み中にエラーが発生した状態 */
    ERROR
}
