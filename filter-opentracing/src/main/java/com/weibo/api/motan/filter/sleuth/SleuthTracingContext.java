/*
 * Copyright 2009-2016 Weibo, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.weibo.api.motan.filter.sleuth;

import com.weibo.api.motan.rpc.RpcContext;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;

/**
 * 
 * @Description SleuthTracingContext hold a SleuthTracerFactory which can replaced by different tracer
 *              implementation.
 * @author zhanglei
 * @date Dec 8, 2016
 *
 */
public class SleuthTracingContext {
    // replace SleuthTracerFactory with any tracer implementation
    public static SleuthTracerFactory tracerFactory = null;
    public static final String ACTIVE_SPAN = "ot_active_span";

    public static Tracer getTracer() {
        return tracerFactory.getTracer();
    }

    public static Span getActiveSpan() {
        Object span = RpcContext.getContext().getAttribute(ACTIVE_SPAN);
        if (span != null && span instanceof Span) {
            return (Span) span;
        }
        return null;
    }

    public static void setActiveSpan(Span span) {
        RpcContext.getContext().putAttribute(ACTIVE_SPAN, span);
    }

    public void setTracerFactory(SleuthTracerFactory tracerFactory) {
        SleuthTracingContext.tracerFactory = tracerFactory;
    }

}
