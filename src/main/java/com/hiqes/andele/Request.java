/*
 * Copyright (C) 2015 HIQES LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hiqes.andele;

import android.os.Handler;

class Request {
    private final RequestOwner      mOwner;
    private final ProtectedAction[] mActions;
    private final Handler           mHandler;

    public Request(RequestOwner owner, ProtectedAction action, Handler handler) {
        mOwner = owner;
        mActions = new ProtectedAction[1];
        mActions[0] = action;
        mHandler = handler;
    }

    public Request(RequestOwner owner, ProtectedAction[] actions, Handler handler) {
        mOwner = owner;
        mActions = actions;
        mHandler = handler;
    }

    public RequestOwner getOwner() {
        return mOwner;
    }

    public int getActionCount() {
        return mActions.length;
    }

    public ProtectedAction getAction() {
        if (mActions.length != 1) {
            throw new IllegalStateException("Request contains more than one action");
        }

        return mActions[0];
    }

    public ProtectedAction[] getActions() {
        return mActions;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public boolean isSameRequest(Request otherRequest) {
        boolean                 ret = false;

        //  See if the protected actions match
        if (mActions.length == otherRequest.mActions.length) {
            boolean             actionsMatch = true;

            for (int i = 0; i < mActions.length; i++) {
                if (!mActions[i].equals(otherRequest.mActions[i])) {
                    actionsMatch = false;
                    break;
                }
            }

            if (actionsMatch) {
                if (mOwner.isSameOwner(otherRequest.getOwner())) {
                    ret = true;
                }
            }
        }

        return ret;
    }
}
