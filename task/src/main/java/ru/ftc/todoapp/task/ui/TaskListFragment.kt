package ru.ftc.todoapp.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_task_list.*
import ru.ftc.todoapp.core.synthetic.exported.exported_toolbar
import ru.ftc.todoapp.task.R
import ru.ftc.todoapp.task.di.TaskDependency
import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.task.presentation.TaskListPresenter
import ru.ftc.todoapp.task.presentation.TaskListPresenterImpl
import ru.ftc.todoapp.task.presentation.TaskListView
import ru.ftc.todoapp.task.presentation.TaskRouter

class TaskListFragment : Fragment(), TaskListView {

    companion object {

        fun newInstance(): Fragment =
            TaskListFragment()
    }

    // FIXME Dependencies from App
    private val dependency: TaskDependency
        get() = activity?.application as TaskDependency

    private lateinit var presenter: TaskListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_task_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exported_toolbar.title = getString(R.string.app_name)
        exported_toolbar.inflateMenu(R.menu.menu_task_list)

        // TODO Providing dependencies from App
        presenter = TaskListPresenterImpl(
            getTasksUseCase = dependency.getTasksUseCase,
            deleteTaskUseCase = dependency.deleteTaskUseCase,
            logoutUseCase = dependency.logoutUseCase,
            router = TaskRouter(activity!!, dependency.loginOpener)
        )
        presenter.attachView(this)

        task_create.setOnClickListener { presenter.onAddClick() }
        exported_toolbar.setOnMenuItemClickListener {
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