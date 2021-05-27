package com.example.ibaadat;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibaadat.databinding.FragmentAudioPlayerBinding;
import com.example.jean.jcplayer.JcPlayerManagerListener;
import com.example.jean.jcplayer.general.JcStatus;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class AudioPlayerFragment extends Fragment {

    ArrayList<String> suraah;
    private FragmentAudioPlayerBinding bind;
    private SurahAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = FragmentAudioPlayerBinding.bind(view);
        getAllSurrah();

        List<JcAudio> audiolist = new ArrayList<>();
        for (String assetName : suraah) {
            String simpleName = assetName.substring(6,assetName.length()-4);
            audiolist.add(JcAudio.createFromAssets(simpleName,assetName));
        }
        bind.jcplayer.initPlaylist(audiolist, new JcPlayerManagerListener() {
            @Override
            public void onPreparedAudio(@NotNull JcStatus jcStatus) {

            }

            @Override
            public void onCompletedAudio() {

            }

            @Override
            public void onPaused(@NotNull JcStatus jcStatus) {

            }

            @Override
            public void onContinueAudio(@NotNull JcStatus jcStatus) {

            }

            @Override
            public void onPlaying(@NotNull JcStatus jcStatus) {

            }

            @Override
            public void onTimeChanged(@NotNull JcStatus jcStatus) {
                updateProgress(jcStatus);
            }



            @Override
            public void onStopped(@NotNull JcStatus jcStatus) {

            }

            @Override
            public void onJcpError(@NotNull Throwable throwable) {

            }
        });
        bind.jcplayer.createNotification();
        adapter = new SurahAdapter(this,R.layout.layout_surrah,audiolist,bind.jcplayer);
        bind.recylerSurrah.setLayoutManager(new LinearLayoutManager(getActivity()));
        bind.recylerSurrah.setAdapter(adapter);

    }

    private void updateProgress(JcStatus jcStatus) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // calculate progress
                float progress = (float) (jcStatus.getDuration() - jcStatus.getCurrentPosition())
                        / (float) jcStatus.getDuration();
                progress = 1.0f - progress;
                adapter.updateProgress(jcStatus.getJcAudio(), progress);
            }
        });
    }

    private void getAllSurrah() {
        suraah  = new ArrayList<>();
        suraah.add("audio/001-al-fatihah.mp3");
        suraah.add("audio/002-al-baqarah.mp3");
        suraah.add("audio/003-al-imran.mp3");
        suraah.add("audio/004-an-nisa.mp3");
        suraah.add("audio/005-al-maidah.mp3");
        suraah.add("audio/006-al-anam.mp3");
        suraah.add("audio/007-al-araf.mp3");
        suraah.add("audio/008-al-anfal.mp3");
        suraah.add("audio/009-at-taubah.mp3");
        suraah.add("audio/010-yunus.mp3");
        suraah.add("audio/011-hud.mp3");
        suraah.add("audio/012-yusuf.mp3");
        suraah.add("audio/013-ar-rad.mp3");
        suraah.add("audio/014-ibrahim.mp3");
        suraah.add("audio/015-al-hijr.mp3");
        suraah.add("audio/016-an-nahl.mp3");
        suraah.add("audio/017-al-isra.mp3");
        suraah.add("audio/018-al-kahf.mp3");
        suraah.add("audio/019-maryam.mp3");
        suraah.add("audio/020-ta-ha.mp3");
        suraah.add("audio/021-al-anbiya.mp3");
        suraah.add("audio/022-al-hajj.mp3");
        suraah.add("audio/023-al-muminun.mp3");
        suraah.add("audio/024-an-nur.mp3");
        suraah.add("audio/025-al-furqan.mp3");
        suraah.add("audio/026-ash-shuara.mp3");
        suraah.add("audio/027-an-naml.mp3");
        suraah.add("audio/028-al-qasas.mp3");
        suraah.add("audio/029-al-ankabut.mp3");
        suraah.add("audio/030-ar-rum.mp3");
        suraah.add("audio/031-luqman.mp3");
        suraah.add("audio/032-as-sajdah.mp3");
        suraah.add("audio/033-al-ahzab.mp3");
        suraah.add("audio/034-saba.mp3");
        suraah.add("audio/035-fatir.mp3");
        suraah.add("audio/036-ya-sin.mp3");
        suraah.add("audio/037-as-saffat.mp3");
        suraah.add("audio/038-sad.mp3");
        suraah.add("audio/039-az-zumar.mp3");
        suraah.add("audio/040-ghafir.mp3");
        suraah.add("audio/041-fussilat.mp3");
        suraah.add("audio/042-ash-shura.mp3");
        suraah.add("audio/043-az-zukhruf.mp3");
        suraah.add("audio/044-ad-dukhan.mp3");
        suraah.add("audio/045-al-jathiyah.mp3");
        suraah.add("audio/046-al-ahqaf.mp3");
        suraah.add("audio/047-muhammad.mp3");
        suraah.add("audio/048-al-fath.mp3");
        suraah.add("audio/049-al-hujurat.mp3");
        suraah.add("audio/050-qaf.mp3");
        suraah.add("audio/051-adh-dhariyat.mp3");
        suraah.add("audio/052-at-tur.mp3");
        suraah.add("audio/053-an-najm.mp3");
        suraah.add("audio/054-al-qamar.mp3");
        suraah.add("audio/055-ar-rahman.mp3");
        suraah.add("audio/056-al-waqiah.mp3");
        suraah.add("audio/057-al-hadid.mp3");
        suraah.add("audio/058-al-mujadilah.mp3");
        suraah.add("audio/059-al-hashr.mp3");
        suraah.add("audio/060-al-mumtahanah.mp3");
        suraah.add("audio/061-as-saff.mp3");
        suraah.add("audio/062-al-jumuah.mp3");
        suraah.add("audio/063-al-munafiqun.mp3");
        suraah.add("audio/064-at-taghabun.mp3");
        suraah.add("audio/065-at-talaq.mp3");
        suraah.add("audio/066-at-tahrim.mp3");
        suraah.add("audio/067-al-mulk.mp3");
        suraah.add("audio/068-al-qalam.mp3");
        suraah.add("audio/069-al-haqqah.mp3");
        suraah.add("audio/070-al-maarij.mp3");
        suraah.add("audio/071-nuh.mp3");
        suraah.add("audio/072-al-jinn.mp3");
        suraah.add("audio/073-al-muzammil.mp3");
        suraah.add("audio/074-al-muddaththir.mp3");
        suraah.add("audio/075-al-qiyamah.mp3");
        suraah.add("audio/076-al-insan.mp3");
        suraah.add("audio/077-al-mursalat.mp3");
        suraah.add("audio/078-an-naba.mp3");
        suraah.add("audio/079-an-naziat.mp3");
        suraah.add("audio/080-abasa.mp3");
        suraah.add("audio/081-at-takwir.mp3");
        suraah.add("audio/082-al-infitar.mp3");
        suraah.add("audio/083-al-mutaffifin.mp3");
        suraah.add("audio/084-al-inshiqaq.mp3");
        suraah.add("audio/085-al-buruj.mp3");
        suraah.add("audio/086-at-tariq.mp3");
        suraah.add("audio/087-al-ala.mp3");
        suraah.add("audio/088-al-ghashiyah.mp3");
        suraah.add("audio/089-al-fajr.mp3");
        suraah.add("audio/090-al-balad.mp3");
        suraah.add("audio/091-ash-shams.mp3");
        suraah.add("audio/092-al-lail.mp3");
        suraah.add("audio/093-ad-duha.mp3");
        suraah.add("audio/094-ash-sharh.mp3");
        suraah.add("audio/095-at-tin.mp3");
        suraah.add("audio/096-al-alaq.mp3");
        suraah.add("audio/097-al-qadr.mp3");
        suraah.add("audio/098-al-baiyyinah.mp3");
        suraah.add("audio/099-az-zalzalah.mp3");
        suraah.add("audio/100-al-adiyat.mp3");
        suraah.add("audio/101-al-qariah.mp3");
        suraah.add("audio/102-at-takathur.mp3");
        suraah.add("audio/103-al-asr.mp3");
        suraah.add("audio/104-al-humazah.mp3");
        suraah.add("audio/105-al-fil.mp3");
        suraah.add("audio/106-quraish.mp3");
        suraah.add("audio/107-al-maun.mp3");
        suraah.add("audio/108-al-kauthar.mp3");
        suraah.add("audio/109-al-kafirun.mp3");
        suraah.add("audio/110-an-nasr.mp3");
        suraah.add("audio/111-al-masad.mp3");
        suraah.add("audio/112-al-ikhlas.mp3");
        suraah.add("audio/113-al-falaq.mp3");
        suraah.add("audio/114-an-nas.mp3");

    }

    class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.Holder> {

        private final LayoutInflater inflater;
        private final List<JcAudio> surrahlist;
        private final int layout;
        private final JcPlayerView jcplayer;
        private SparseArray<Float> progressMap = new SparseArray<>();

        public SurahAdapter(Fragment fragment, int layout, List<JcAudio> surrahlist, JcPlayerView jcplayer) {
            inflater = LayoutInflater.from(fragment.getActivity());
            this.surrahlist = surrahlist;
            this.layout = layout;
            this.jcplayer = jcplayer;
        }

        @NonNull
        @NotNull
        @Override
        public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            return new Holder(inflater.inflate(layout, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
            JcAudio path = surrahlist.get(position);
            holder.bind(path.getPath(), position);
        }

        @Override
        public int getItemCount() {
            return surrahlist.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            TextView textNum, textName;

            public Holder(@NonNull @NotNull View itemView) {
                super(itemView);
                textNum = itemView.findViewById(R.id.text_number);
                textName = itemView.findViewById(R.id.textName);
                itemView.setOnClickListener(v -> {
                    jcplayer.playAudio(jcplayer.getMyPlaylist().get(getAdapterPosition()));
                });
            }

            public void bind(String path, int position) {
                textName.setText(path.split("\\.")[0]);
                textNum.setText(String.valueOf(position + 1));
            }
        }

        public void updateProgress(JcAudio jcAudio, float progress) {
            int position = surrahlist.indexOf(jcAudio);


            progressMap.put(position, progress);
            if (progressMap.size() > 1) {
                for (int i = 0; i < progressMap.size(); i++) {
                    if (progressMap.keyAt(i) != position) {
                        notifyItemChanged(progressMap.keyAt(i));
                        progressMap.delete(progressMap.keyAt(i));
                    }
                }
            }
            notifyItemChanged(position);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        bind.jcplayer.createNotification();
    }
    @Override
    public void onPause() {
        super.onPause();
        bind.jcplayer.createNotification();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.jcplayer.kill();
    }
}