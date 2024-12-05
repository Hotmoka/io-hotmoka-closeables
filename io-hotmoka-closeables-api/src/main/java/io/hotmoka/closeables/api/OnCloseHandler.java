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

package io.hotmoka.closeables.api;

/**
 * Code executed when the object gets closed.
 */
public interface OnCloseHandler {

	/**
	 * The code to execute when the object gets closed.
	 * Note that this method throws no exception: if the implementation code
	 * encounters an {@code InterruptedException}, then it should catch it and set the
	 * interruption flag; any other kind of exception should just be logged.
	 */
	void close();
}