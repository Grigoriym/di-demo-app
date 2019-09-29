package ru.ftc.todoapp.feature.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_task.*
import kotlinx.android.synthetic.main.toolbar_dark.*
import ru.ftc.todoapp.R
import ru.ftc.todoapp.app.BaseFragment
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.feature.task.presentation.TaskPresenter
import ru.ftc.todoapp.feature.task.presentation.TaskView
import javax.inject.Inject

var Bundle.task: Task?
    get() = getSerializable("TASK") as? Task
    set(value) = putSerializable("TASK", value)

class TaskFragment : BaseFragment(), TaskView {

    companion object {

        fun newInstance(task: Task?): Fragment =
            TaskFragment().apply {
                arguments = Bundle().apply { this.task = task }
            }
    }

    @Inject
    lateinit var presenter: TaskPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_task, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.edit_task)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
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

