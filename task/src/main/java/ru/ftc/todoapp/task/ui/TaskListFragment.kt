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
import ru.ftc.todoapp.task.di.DaggerTaskComponent
import ru.ftc.todoapp.task.di.TaskDependency
import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.task.presentation.TaskListPresenter
import ru.ftc.todoapp.task.presentation.TaskListView
import javax.inject.Inject
import javax.inject.Provider

class TaskListFragment : Fragment(), TaskListView {

    companion object {

        fun newInstance(): Fragment =
            TaskListFragment()
    }

    @Inject
    internal lateinit var presenter: TaskListPresenter
    @Inject
    internal lateinit var itemTouchHelper: Provider<ItemTouchHelper>
    @Inject
    internal lateinit var adapter: Provider<TaskAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerTaskComponent.factory()
            .create(
                activity!!,
                null,
                (activity?.application as TaskDependency.DepProvider).taskDependency()
            )
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_task_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exported_toolbar.title = getString(R.string.app_name)
        exported_toolbar.inflateMenu(R.menu.menu_task_list)

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
        val adapter = adapter.get()
        adapter.items = tasks
        task_recycler.adapter = adapter

        itemTouchHelper.get().attachToRecyclerView(task_recycler)
    }
}