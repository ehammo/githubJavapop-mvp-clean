package com.ehammo.githubjavapop_mvp_clean;

import android.util.Log;

import com.ehammo.githubjavapop_mvp_clean.data.model.Repository;
import com.ehammo.githubjavapop_mvp_clean.data.model.RepositoryCollection;
import com.ehammo.githubjavapop_mvp_clean.data.repository.DataSource.RepositoryCallback;
import com.ehammo.githubjavapop_mvp_clean.data.repository.IDataStore;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

public class CacheTest {

    @Test
    public void whenGotNewInfo_thenUpdateCache(){
        IDataStore dataStore = mock(IDataStore.class);

//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                System.out.println("DEBUG: " + "DataStore" + ": " + invocation.toString());
//                Object[] args = invocation.getArguments();
//                System.out.println("DEBUG: " + "Args" + ": " + args.toString());
////                Mock mock = invocation.getMock();
//                return null;
//            }
//        }).when(dataStore).listRepositories(any(RepositoryCallback.class), eq(1));

        RepositoryCallback repositoryCallback = mock(RepositoryCallback.class);
        dataStore.listRepositories(repositoryCallback, 1);
//        verify(repositoryCallback, atLeastOnce()).listRepositories(any(RepositoryCollection.class));
                                                            
    }

    @Test
    public void passedTime_invalidateCache(){
        assert true;
    }

}
