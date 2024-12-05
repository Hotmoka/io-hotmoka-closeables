/*
Copyright 2024 Fausto Spoto

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package io.hotmoka.closeables.internal;

import io.hotmoka.annotations.ThreadSafe;
import io.hotmoka.closeables.OnCloseHandlersManagers;
import io.hotmoka.closeables.api.OnCloseHandler;
import io.hotmoka.closeables.api.OnCloseHandlersContainer;
import io.hotmoka.closeables.api.OnCloseHandlersManager;

/**
 * Partial implementation of an autocloseable with support close handlers, that must be called when the
 * object gets closed.
 */
@ThreadSafe
public abstract class AutoCloseableWithOnCloseHandlersImpl implements OnCloseHandlersContainer, AutoCloseable {

	/**
	 * The close handlers manager.
	 */
	private final OnCloseHandlersManager manager = OnCloseHandlersManagers.create();

	/**
	 * Creates the autocloseable.
	 */
	protected AutoCloseableWithOnCloseHandlersImpl() {}

	@Override
	public void addOnCloseHandler(OnCloseHandler handler) {
		manager.addOnCloseHandler(handler);
	}

	@Override
	public void removeOnCloseHandler(OnCloseHandler handler) {
		manager.removeOnCloseHandler(handler);
	}

	/**
	 * Calls all close handlers added to this object.
	 */
	protected void callCloseHandlers() {
		manager.callCloseHandlers();
	}
}