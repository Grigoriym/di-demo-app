package ru.ftc.todoapp.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_task.*
import ru.ftc.todoapp.core.synthetic.exported.exported_toolbar
import ru.ftc.todoapp.task.R
import ru.ftc.todoapp.task.di.DaggerTaskComponent
import ru.ftc.todoapp.task.di.TaskDependency
import ru.ftc.todoapp.task.domain.entity.Task
import ru.ftc.todoapp.task.presentation.TaskPresenter
import ru.ftc.todoapp.task.presentation.TaskView
import javax.inject.Inject

class TaskFragment : Fragment(), TaskView {

    companion object {

        fun newInstance(task: Task?): Fragment =
            TaskFragment().apply {
                arguments = Bundle().apply { this.task = task }
            }
    }

    private var Bundle.task: Task?
        get() = getSerializable("TASK") as? Task
        set(value) = putSerializable("TASK", value)

    @Inject
    internal lateinit var presenter: TaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerTaskComponent.factory()
            .create(
                activity!!,
                arguments?.task,
                (activity?.application as TaskDependency.DepProvider).taskDependency()
            )
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_task, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exported_toolbar.title = getString(R.string.edit_task)
        exported_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        exported_toolbar.setNavigationOnClickListener {
            presenter.onBackClick()
        }

        presenter.attachView(this)

        task_save.setOnClickListener {
            presenter.onSaveClick(task_description.text?.toString() ?: "")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showTask(task: Task) {
        task_description.setText(task.description)
    }
}

