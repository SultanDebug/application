package com.hzq.democlient.service;

import com.hzq.democlient.SocketReqDTO;

/**
 * @author Huangzq
 * @title: SocketPubService
 * @projectName qs-saas
 * @date 2019/12/31 10:31
 */
public interface SocketPubService {
    String pubMsg(SocketReqDTO socketReqDTO);
}
