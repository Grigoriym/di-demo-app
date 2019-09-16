package ru.ftc.todoapp.feature.task.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.android.synthetic.main.toolbar_dark.*
import ru.ftc.todoapp.R
import ru.ftc.todoapp.app.App
import ru.ftc.todoapp.app.sl.CachingFactory
import ru.ftc.todoapp.app.sl.get
import ru.ftc.todoapp.app.sl.serviceLocator
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.feature.task.presentation.TaskListPresenter
import ru.ftc.todoapp.feature.task.presentation.TaskListPresenterImpl
import ru.ftc.todoapp.feature.task.presentation.TaskListView
import ru.ftc.todoapp.navigation.Router

class TaskListFragment : Fragment(), TaskListView {

    companion object {

        fun newInstance(): Fragment =
            TaskListFragment()
    }

    private val presenter: TaskListPresenter by serviceLocator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            App.serviceLocator.add(
                TaskListPresenter::class,
                CachingFactory { TaskListPresenterImpl(get(), get(), get(), get()) }
            )
        }
    }

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