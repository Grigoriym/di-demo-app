package ru.ftc.todoapp.feature.task.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_task.*
import kotlinx.android.synthetic.main.toolbar_dark.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.task.domain.entity.Task
import ru.ftc.todoapp.feature.task.presentation.TaskPresenter
import ru.ftc.todoapp.feature.task.presentation.TaskView
import ru.ftc.todoapp.feature.task.presentation.di.taskPresentationKodeinModule

class TaskFragment : Fragment(), TaskView, KodeinAware {
    override lateinit var kodein: Kodein

    companion object {

        fun newInstance(task: Task): Fragment =
            TaskFragment().apply { this.task = task}
    }

    private var task: Task
        get() = (arguments?.getSerializable("TASK") as? Task) ?: Task()
        set(value) { arguments = Bundle().apply { this.putSerializable("TASK", value)}}


    private fun initKodein() {
        kodein = Kodein {
            extend((activity as KodeinAware).kodein)
            import(taskPresentationKodeinModule)
            bind<Fragment>() with provider { this@TaskFragment }
            bind<Task>() with provider { this@TaskFragment.task }
        }
    }


    private lateinit var presenter: TaskPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKodein()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_task, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = getString(R.string.edit_task)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            presenter.onBackClick()
        }

        presenter = direct.instance()
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

