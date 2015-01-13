/*
 * Copyright (C) 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings("nullness")
/** An ordering that tries several comparators in order. */
@GwtCompatible(serializable = true)
    final class CompoundOrdering<T extends /*@Nullable*/ Object> extends Ordering<T> implements Serializable {
    final /*@Nullable*/ ImmutableList<Comparator<? super T>> comparators = null;
  
  CompoundOrdering(Comparator<? super T> primary,
      Comparator<? super T> secondary) {
      //this.comparators
      //       = ImmutableList.<Comparator<? super T>>of(primary, secondary);
  }

  CompoundOrdering(Iterable<? extends Comparator<? super T>> comparators) {
      //this.comparators = ImmutableList.copyOf(comparators);
  }

  CompoundOrdering(List<? extends Comparator<? super T>> comparators,
      Comparator<? super T> lastComparator) {
      //this.comparators = new ImmutableList.Builder<Comparator<? super T>>()
      //   .addAll(comparators).add(lastComparator).build();
  }
  
 
  /*@Pure*/ public int compare(T left, T right) {
    for (Comparator<? super T> comparator : comparators) {
      int result = comparator.compare(left, right);
      if (result != 0) {
        return result;
      }
    }
    return 0;
  }
  
  @Pure @Override public boolean equals(/*@Nullable*/ Object object) {
    if (object == this) {
      return true;
    }
    if (object instanceof CompoundOrdering) {
      CompoundOrdering<?> that = (CompoundOrdering<?>) object;
      return this.comparators.equals(that.comparators);
    }
    return false;
  }

  @Pure @Override public int hashCode() {
    return comparators.hashCode();
  }

  @Pure @Override public String toString() {
    return "Ordering.compound(" + comparators + ")";
  }

  private static final long serialVersionUID = 0;
}
