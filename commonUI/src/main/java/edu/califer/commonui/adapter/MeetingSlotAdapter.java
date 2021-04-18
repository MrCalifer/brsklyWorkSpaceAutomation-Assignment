package edu.califer.commonui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.califer.commonui.R;
import edu.califer.commonui.databinding.MeetingBookingSlotRecyclerItemBinding;
import edu.califer.cvo.meetingModels.MeetingDataModel;
import edu.califer.cvo.meetingModels.MeetingDatum;

public class MeetingSlotAdapter extends RecyclerView.Adapter<MeetingSlotAdapter.MeetingViewHolder> {

    private MeetingDataModel model;

    private MeetingBookingSlotRecyclerItemBinding binding;

    public MeetingSlotAdapter(MeetingDataModel model) {
        this.model = model;
    }


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MeetingBookingSlotRecyclerItemBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
        return new MeetingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        holder.bind(model.getData().get(position));
    }

    @Override
    public int getItemCount() {
        return model.getData().size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder {

        public MeetingViewHolder(@NonNull MeetingBookingSlotRecyclerItemBinding binding) {
            super(binding.getRoot());
        }

        public void bind(@NonNull MeetingDatum model){
            binding.setTime(model.getTimeSlot());
            if (model.getIsBooked()){
                binding.outerLayout.setBackgroundResource(R.drawable.slot_booked);
            }
        }
    }
}
