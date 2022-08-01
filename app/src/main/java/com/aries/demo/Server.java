package com.aries.demo;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.rx3.Rx3Apollo;

import com.aries.JobsOverviewQuery;
import com.aries.JobDetailQuery;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;

class Server {
    private static ApolloClient apolloClient;

    public static Observable<Response<JobsOverviewQuery.Data>> fetchJobs(String type) {
        ApolloQueryCall<JobsOverviewQuery.Data> call = getApolloClient()
                .query(new JobsOverviewQuery());

        return Rx3Apollo.from(call)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter((dataResponse -> dataResponse.getData() != null));
    }

    private static ApolloClient getApolloClient() {
        if (apolloClient == null) {
            //Build the Apollo Client
            String serverUrl = "https://api.graphal.jobs/";
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            apolloClient = ApolloClient.builder()
                    .serverUrl(serverUrl)
                    .okHttpClient(okHttpClient)
                    .build();
        }

        return apolloClient;
    }
}
