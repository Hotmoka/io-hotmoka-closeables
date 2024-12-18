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

package io.hotmoka.closeables;

import io.hotmoka.closeables.api.ClosureLock;
import io.hotmoka.closeables.internal.ClosureLockImpl;

/**
 * Provider of closure locks.
 */
public abstract class ClosureLocks {

	private ClosureLocks() {}

	/**
	 * Yields a new closure lock.
	 * 
	 * @return the new closure lock
	 */
	public static ClosureLock create() {
		return new ClosureLockImpl();
	}
}