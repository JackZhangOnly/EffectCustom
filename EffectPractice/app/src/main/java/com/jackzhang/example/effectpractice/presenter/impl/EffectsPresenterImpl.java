package com.jackzhang.example.effectpractice.presenter.impl;

import com.jackzhang.example.effectpractice.presenter.EffectsPresenter;

/**
 * Created by Jack on 2017/5/14.
 */

public class EffectsPresenterImpl implements EffectsPresenter {
    private EffectsPresenter.View mView;

    public EffectsPresenterImpl(View view){
        this.mView=view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }
}
