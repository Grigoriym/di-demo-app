package ru.ftc.todoapp.feature.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.fragment_task_list.*
import kotlinx.android.synthetic.main.toolbar_dark.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.feature.task.presentation.TaskListPresenter
import ru.ftc.todoapp.feature.task.presentation.TaskListView
import ru.ftc.todoapp.feature.task.presentation.di.taskListPresentationKodeinModule

class TaskListFragment : Fragment(), TaskListView, KodeinAware {
    override lateinit var kodein: Kodein

    companion object {
        fun newInstance(): Fragment =
            TaskListFragment()
    }

    private fun initKodein() {
        kodein = Kodein {
            extend((activity as KodeinAware).kodein)
            import(taskListPresentationKodeinModule)
            bind<TaskAdapter>() with provider { TaskAdapter(instance(), instance()) }
        }
    }

    private lateinit var presenter: TaskListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKodein()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_task_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.menu_task_list)

        presenter = direct.instance()
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
        val adapter by instance<TaskAdapter>()
        adapter.items = tasks
        task_recycler.adapter = adapter

        ItemTouchHelper(TaskListTouchCallback()).attachToRecyclerView(task_recycler)
    }
}