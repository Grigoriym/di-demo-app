package ru.ftc.todoapp.feature.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.android.synthetic.main.toolbar_dark.*
import ru.ftc.todoapp.R
import ru.ftc.todoapp.app.BaseFragment
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.feature.task.presentation.TaskListPresenter
import ru.ftc.todoapp.feature.task.presentation.TaskListView
import javax.inject.Inject

class TaskListFragment : BaseFragment(), TaskListView {

    companion object {

        fun newInstance(): Fragment =
            TaskListFragment()
    }

    @Inject
    lateinit var presenter: TaskListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_task_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.menu_task_list)

        presenter.attachView(this)

        task_create.setOnClickListener { presenter.onAddClick() }
        toolbar.setOnMenuItemClickListener {
            presenter.exit()
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showTasks(tasks: List<Task>) {
        val adapter = TaskAdapter(presenter::onTaskClick, presenter::onTaskSwipe)
        adapter.items = tasks
        task_recycler.adapter = adapter

        ItemTouchHelper(TaskListTouchCallback()).attachToRecyclerView(task_recycler)
    }
}