package ru.ftc.todoapp.core.synthetic.exported

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.toolbar_dark.*

inline val Fragment.exported_toolbar: Toolbar get() = toolbar