/*
 * Copyright (c) 2016  W.I.S.V. 'Christiaan Huygens'
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.wisv.choice.util

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class ResponseEntityBuilder {

    companion object {

        fun createResponseEntity(httpStatus: HttpStatus, message: String, content: Any? = null, httpHeaders: HttpHeaders? = null): ResponseEntity<*> {
            val responseBody = LinkedHashMap<String, Any>()

            responseBody.put("status", httpStatus.toString())
            responseBody.put("timestamp", LocalDateTime.now().toString())
            responseBody.put("message", message)

            if (content != null) {
                responseBody.put("content", content)
            }

            if (httpHeaders == null) {
                val httpHeaders = HttpHeaders()
                httpHeaders.set("Content-Type", "application/json")
            }

            return ResponseEntity(responseBody, httpHeaders, httpStatus)
        }
    }
}