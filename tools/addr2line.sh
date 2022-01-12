#!/bin/sh
#
#     This file is part of NetGuard.
#     NetGuard is free software: you can redistribute it and/or modify
#     it under the terms of the GNU General Public License as published by
#     the Free Software Foundation, either version 3 of the License, or
#     (at your option) any later version.
#     NetGuard is distributed in the hope that it will be useful,
#     but WITHOUT ANY WARRANTY; without even the implied warranty of
#     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#     GNU General Public License for more details.
#     You should have received a copy of the GNU General Public License
#     along with NetGuard.  If not, see <http://www.gnu.org/licenses/>.
#     Copyright 2015-2019 by Marcel Bokhorst (M66B)
#

addr2line=/media/marcel/C720/android-ndk-r10e/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64/bin/arm-linux-androideabi-addr2line
lib=./app/build/intermediates/cmake/debug/obj/arm64-v8a/libnetguard.so
addr2line -C -f -e $lib $1
