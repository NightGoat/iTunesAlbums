package ru.nightgoat.itunesalbums.presentation.search

/**
 * Интерфейс для вызова Фрагмента с информацией об альбоме из ViewHolder.
 * ViewHolder -> ListAdapter -> Fragment -> Activity
 * @author NightGoat
 */
interface FragmentCallbacks {
    fun goToAlbumFragment(albumId: Long)
}