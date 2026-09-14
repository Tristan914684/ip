# Clsl User Guide

![Clsl graphical user interface](Ui.png)


Clsl is a simple task manager for to-dos, deadlines, and events.

## Getting started

Run the application from the project directory with:

```text
./gradlew run
```

On Windows, use:

```text
gradlew.bat run
```

The GUI opens with a resizable chat window. Enter a command in the input field
at the bottom and press **Enter** or select **Send**.

## Adding tasks

Add a to-do with:

```text
todo <description>
```

Add a deadline with:

```text
deadline <description> /by <yyyy-mm-dd>
```

Add an event with:

```text
event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>
```

Descriptions can contain spaces. Dates must be valid calendar dates in
`yyyy-mm-dd` format. For events, the start date must be earlier than or equal
to the end date.

## Viewing tasks

List every task:

```text
list
```

Find tasks whose descriptions contain a keyword:

```text
find <keyword>
```

Find tasks occurring on a date. This includes deadlines due on that date and
events covering that date:

```text
on <yyyy-mm-dd>
```

## Updating tasks

Task numbers are shown by the `list` command and start at `1`.

Mark a task as done:

```text
mark <task number>
```

Mark a task as not done:

```text
unmark <task number>
```

Delete a task:

```text
delete <task number>
```

After a task is deleted, the remaining tasks are displayed with consecutive
task numbers.

## Exiting

Close the application with:

```text
bye
```

## Error handling

Clsl reports command errors without terminating the application. Error
responses begin with `ERROR:`.

Examples of handled errors include:

- an unknown command or missing command parameter;
- a task number that is missing, not a whole number, zero, negative, or outside
  the current task list;
- an invalid date such as `2026-02-30`;
- an event whose start date is later than its end date; and
- an attempt to add a duplicate task.

## Duplicate tasks

Clsl rejects a newly added task when an existing task of the same type has the same description,
ignoring capitalization and whitespace. Deadlines must also have the same due date, and events
must also have the same start and end dates.

For example, after adding `todo Buy milk`, the following command is rejected:

```text
todo BU Y   MILK
```

The response is:

```text
ERROR: already added
```

Completion status does not make an otherwise identical task different. When duplicate tasks are
found in an older data file, Clsl keeps the first occurrence, removes the later occurrences, and
rewrites the file with consecutive task indices.

## Data storage

Tasks are saved in `data/csls.txt` and loaded when Clsl starts. If the file is
not found, Clsl starts with an empty task list and creates the required
directory when a task is first saved.

