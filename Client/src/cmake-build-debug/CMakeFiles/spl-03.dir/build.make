# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /snap/clion/138/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /snap/clion/138/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/spl211/IdeaProjects/Assignment3/Client/src

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/spl-03.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/spl-03.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/spl-03.dir/flags.make

CMakeFiles/spl-03.dir/Client.cpp.o: CMakeFiles/spl-03.dir/flags.make
CMakeFiles/spl-03.dir/Client.cpp.o: ../Client.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/spl-03.dir/Client.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/spl-03.dir/Client.cpp.o -c /home/spl211/IdeaProjects/Assignment3/Client/src/Client.cpp

CMakeFiles/spl-03.dir/Client.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/spl-03.dir/Client.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/IdeaProjects/Assignment3/Client/src/Client.cpp > CMakeFiles/spl-03.dir/Client.cpp.i

CMakeFiles/spl-03.dir/Client.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/spl-03.dir/Client.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/IdeaProjects/Assignment3/Client/src/Client.cpp -o CMakeFiles/spl-03.dir/Client.cpp.s

CMakeFiles/spl-03.dir/connectionHandler.cpp.o: CMakeFiles/spl-03.dir/flags.make
CMakeFiles/spl-03.dir/connectionHandler.cpp.o: ../connectionHandler.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/spl-03.dir/connectionHandler.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/spl-03.dir/connectionHandler.cpp.o -c /home/spl211/IdeaProjects/Assignment3/Client/src/connectionHandler.cpp

CMakeFiles/spl-03.dir/connectionHandler.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/spl-03.dir/connectionHandler.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/IdeaProjects/Assignment3/Client/src/connectionHandler.cpp > CMakeFiles/spl-03.dir/connectionHandler.cpp.i

CMakeFiles/spl-03.dir/connectionHandler.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/spl-03.dir/connectionHandler.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/IdeaProjects/Assignment3/Client/src/connectionHandler.cpp -o CMakeFiles/spl-03.dir/connectionHandler.cpp.s

CMakeFiles/spl-03.dir/ConnectionReader.cpp.o: CMakeFiles/spl-03.dir/flags.make
CMakeFiles/spl-03.dir/ConnectionReader.cpp.o: ../ConnectionReader.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/spl-03.dir/ConnectionReader.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/spl-03.dir/ConnectionReader.cpp.o -c /home/spl211/IdeaProjects/Assignment3/Client/src/ConnectionReader.cpp

CMakeFiles/spl-03.dir/ConnectionReader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/spl-03.dir/ConnectionReader.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/IdeaProjects/Assignment3/Client/src/ConnectionReader.cpp > CMakeFiles/spl-03.dir/ConnectionReader.cpp.i

CMakeFiles/spl-03.dir/ConnectionReader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/spl-03.dir/ConnectionReader.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/IdeaProjects/Assignment3/Client/src/ConnectionReader.cpp -o CMakeFiles/spl-03.dir/ConnectionReader.cpp.s

CMakeFiles/spl-03.dir/KeyboardReader.cpp.o: CMakeFiles/spl-03.dir/flags.make
CMakeFiles/spl-03.dir/KeyboardReader.cpp.o: ../KeyboardReader.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/spl-03.dir/KeyboardReader.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/spl-03.dir/KeyboardReader.cpp.o -c /home/spl211/IdeaProjects/Assignment3/Client/src/KeyboardReader.cpp

CMakeFiles/spl-03.dir/KeyboardReader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/spl-03.dir/KeyboardReader.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/IdeaProjects/Assignment3/Client/src/KeyboardReader.cpp > CMakeFiles/spl-03.dir/KeyboardReader.cpp.i

CMakeFiles/spl-03.dir/KeyboardReader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/spl-03.dir/KeyboardReader.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/IdeaProjects/Assignment3/Client/src/KeyboardReader.cpp -o CMakeFiles/spl-03.dir/KeyboardReader.cpp.s

# Object files for target spl-03
spl__03_OBJECTS = \
"CMakeFiles/spl-03.dir/Client.cpp.o" \
"CMakeFiles/spl-03.dir/connectionHandler.cpp.o" \
"CMakeFiles/spl-03.dir/ConnectionReader.cpp.o" \
"CMakeFiles/spl-03.dir/KeyboardReader.cpp.o"

# External object files for target spl-03
spl__03_EXTERNAL_OBJECTS =

spl-03: CMakeFiles/spl-03.dir/Client.cpp.o
spl-03: CMakeFiles/spl-03.dir/connectionHandler.cpp.o
spl-03: CMakeFiles/spl-03.dir/ConnectionReader.cpp.o
spl-03: CMakeFiles/spl-03.dir/KeyboardReader.cpp.o
spl-03: CMakeFiles/spl-03.dir/build.make
spl-03: CMakeFiles/spl-03.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking CXX executable spl-03"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/spl-03.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/spl-03.dir/build: spl-03

.PHONY : CMakeFiles/spl-03.dir/build

CMakeFiles/spl-03.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/spl-03.dir/cmake_clean.cmake
.PHONY : CMakeFiles/spl-03.dir/clean

CMakeFiles/spl-03.dir/depend:
	cd /home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/spl211/IdeaProjects/Assignment3/Client/src /home/spl211/IdeaProjects/Assignment3/Client/src /home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug /home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug /home/spl211/IdeaProjects/Assignment3/Client/src/cmake-build-debug/CMakeFiles/spl-03.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/spl-03.dir/depend

