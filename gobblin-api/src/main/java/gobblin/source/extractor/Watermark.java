/*
 * Copyright (C) 2014-2015 LinkedIn Corp. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 */

package gobblin.source.extractor;

import com.google.gson.JsonElement;

/**
 * A {@link Watermark} represents a checkpoint in data processing, and indicates that all data up to  specific point has
 * been pulled from some source. Implementations of this interface are responsible for defining data structures in order
 * to track this state.
 *
 * <p>
 *  A {@link Watermark} will be serialized in {@link gobblin.source.workunit.WorkUnit}s and
 *  {@link gobblin.configuration.WorkUnitState}s. The {@link #toJson()} method will be used to serialize the
 *  {@link Watermark} into a {@link JsonElement}.
 * </p>
 */
public interface Watermark {

  /**
   * Convert this {@link Watermark} into a {@link JsonElement}.
   * @return a {@link JsonElement} representing this {@link Watermark}.
   */
  public JsonElement toJson();

  /**
   * This method must return a value from [0, 100]. The value should correspond to a percent completion. Given two
   * {@link Watermark} values, where the lowWatermark is the starting point, and the highWatermark is the goal, what
   * is the percent completion of this {@link Watermark}.
   *
   * @param lowWatermark is the starting {@link Watermark} for the percent completion calculation. So if this.equals(lowWatermark) is true, this method should return 0.
   * @param highWatermark is the end value {@link Watermark} for the percent completion calculation. So if this.equals(highWatermark) is true, this method should return 100.
   * @return a value from [0, 100] representing the percentage completion of this {@link Watermark}.
   */
  public short calculatePercentCompletion(Watermark lowWatermark, Watermark highWatermark);
}
