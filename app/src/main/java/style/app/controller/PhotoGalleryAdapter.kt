package style.app.controller

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import style.app.R
import style.app.model.Photo

class PhotoGalleryAdapter(val photos: List<Photo>,
                          private val photoWidth: Int, private val photoHeight: Int,
                          val onClickFunction: (Photo) -> Unit)
    : RecyclerView.Adapter<PhotoGalleryAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val photoView = inflater.inflate(R.layout.gallery_item, parent, false)
        return PhotoViewHolder(photoView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        val imageView = holder.photoImageView

        Picasso.get()
            .load(photo.uri)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error)
            .resize(photoWidth, photoHeight)
            .centerCrop()
            .into(imageView)
    }

    inner class PhotoViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var photoImageView: ImageView = itemView.findViewById(R.id.gallery_photo)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val photo = photos[position]
                onClickFunction(photo)
            }
        }
    }


}
