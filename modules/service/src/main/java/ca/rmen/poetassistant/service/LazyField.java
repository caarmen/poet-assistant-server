/*
 * Copyright (c) 2021 - present Carmen Alvarez
 *
 * This file is part of Poet Assistant.
 *
 * Poet Assistant is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Poet Assistant is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Poet Assistant.  If not, see <http://www.gnu.org/licenses/>.
 */

package ca.rmen.poetassistant.service;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

// TODO eee if we can use the Lazy annotation from spring to reduce this boilerplate
public class LazyField<T> implements Supplier<T> {
    private final AtomicReference<T> value = new AtomicReference<>(null);
    private final Supplier<T> supplier;

    public LazyField(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (value.get() == null) {
            value.compareAndSet(
                    null,
                    supplier.get()
            );
        }
        return value.get();
    }
}
