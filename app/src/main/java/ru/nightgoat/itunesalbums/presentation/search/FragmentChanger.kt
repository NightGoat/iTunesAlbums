package ru.nightgoat.itunesalbums.presentation.search

import androidx.fragment.app.Fragment

/**
 * Интерфейс для активити, чтобы переключать фрагменты из фрагментов
 * @author NightGoat
 */
interface FragmentChanger {
    fun changeFragment(fragment: Fragment)
    fun popBackStack()
}