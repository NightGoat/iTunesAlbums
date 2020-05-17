package ru.nightgoat.itunesalbums.presentation

/**
 * Интерфейс для вызова Фрагмента с информацией об альбоме из ViewHolder.
 * ViewHolder -> ListAdapter -> Fragment -> Activity
 * @author NightGoat
 */
interface OnItemClickListener {
    fun onItemClick(albumId: Long)
}