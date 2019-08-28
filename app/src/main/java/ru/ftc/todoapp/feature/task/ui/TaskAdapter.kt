package ru.ftc.todoapp.feature.task.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_list_item.view.*
import ru.ftc.todoapp.R
import ru.ftc.todoapp.feature.task.domain.entity.Task

class TaskAdapter(
    private val clickListener: (Task) -> Unit,
    private val swipeOutListener: (Task) -> Unit
) : RecyclerView.Adapter<TaskViewHolder>() {

    var items: List<Task> = listOf()

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int =
        items.size

    override fun getItemId(position: Int): Long =
        items[position].id.hashCode().toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(parent, clickListener, ::swipeOutItem)

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun swipeOutItem(position: Int) {
        val swipedItem = items[position]
        items = items.minus(swipedItem)
        notifyItemRemoved(position)
        swipeOutListener(swipedItem)
    }
}

class TaskViewHolder(
    parent: ViewGroup,
    private val clickListener: (Task) -> Unit,
    private val swipeOutListener: (Int) -> Unit
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)) {

    fun bind(task: Task) {
        itemView.task_title.text = task.description
        itemView.setOnClickListener { clickListener(task) }
    }

    fun swipeOut() {
        swipeOutListener(adapterPosition)
    }
}