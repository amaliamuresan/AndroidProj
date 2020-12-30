package com.example.groupupv2.domain;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.groupupv2.data.PostDto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import timber.log.Timber;

public class PostMediator {
    private final PostRepository remoteRepository;
    private final ExecutorService executorService;
    private final MutableLiveData<List<Post>> mutableLiveData;


    public PostMediator(PostRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
        this.executorService = Executors.newSingleThreadExecutor();
        this.mutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Post>> getItems() {
        ArrayList<Post> list = new ArrayList<>();

        executorService.execute(() -> {
            for (PostDto dto : remoteRepository.getItems().dtosList) {
                if(dto != null)
                    list.add(new Post(dto.getAutorID(), dto.getImage(), timestampToDate(dto.getData()), dto.getDescription(), dto.getDomain()));
            }
            this.mutableLiveData.postValue(list);
        });
        return mutableLiveData;

    }

    public void postItem(PostDto post)
    {
        executorService.execute(() -> remoteRepository.postItem(post));
    }

    public int getLastItem()
    {
        final Integer[] rez = {new Integer(0)};
        executorService.execute(() -> { rez[0] = remoteRepository.getLastItem();});

        return rez[0];
    }

    public static String timestampToDate(String postDate) {
        Timestamp timestamp = new Timestamp(Long.parseLong(postDate));
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy").withZone(zoneId);
        df.format(Instant.ofEpochMilli(Long.parseLong(postDate)));

        return timestamp.toInstant().atZone(zoneId).toLocalDateTime().format(df);
    }
}
