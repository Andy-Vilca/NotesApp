package pe.avilca.notesapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import pe.avilca.notesapp.R;
import pe.avilca.notesapp.models.Notes;
import pe.avilca.notesapp.repositories.NotesRepository;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> notes;

    public NotesAdapter(List<Notes> notes){
        this.notes = notes;
    }

    public void setNotes(List<Notes> notes){
        this.notes = notes;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title, content;
        public ImageButton btn_favorite, btn_archive;

        public ViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.inputTitle);
            content=itemView.findViewById(R.id.description);
            btn_favorite=itemView.findViewById(R.id.doFavorite);
            btn_archive=itemView.findViewById(R.id.doArchived);
        }
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notes, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder viewHolder, final int position) {
        final Notes note = this.notes.get(position);
        viewHolder.title.setText(note.getTitle());
        viewHolder.content.setText(note.getContent());

        viewHolder.btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesRepository.updateState("favorite", note.getId());
            }
        });

        viewHolder.btn_archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesRepository.updateState("archive", note.getId());
            }
        });

    }

    @Override
    public int getItemCount(){
        return this.notes.size();
    }


}
