package Interface

interface CardListener {
    fun OnEditClick(position: Int)
    fun OnDeleteClick(position: Int)
    fun OnSoundClick(position: Int)
    fun OnFeedClick(position: Int)
}