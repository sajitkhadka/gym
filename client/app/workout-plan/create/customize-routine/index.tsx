import { PredefinedPlanSchedule } from '@/app/lib/api/predefined-plan';
import { UserPlanScheduleDto } from '@/app/lib/api/workout-routine';
import { DaysofWeek, getDaysOfWeek } from '@/app/lib/util';
import { uuid } from '@/app/lib/uuid';
import { DndContext, DragEndEvent, DragOverlay, DragStartEvent, useDraggable, useDroppable } from '@dnd-kit/core';
import React, { useEffect, useState } from 'react';

const REST_CATEGORY_ID = 10;

export interface Day {
    id: number,
    date: string,
    dayOfWeek: keyof typeof DaysofWeek
    predefinedSchedule?: PredefinedPlanSchedule
}


function getNext7Days() {
    const currentDate = new Date();
    const result: Day[] = [];
    for (var i = 0; i < 7; i++) {
        const nextDate = new Date(currentDate);
        nextDate.setDate(currentDate.getDate() + i);
        result.push({
            id: (i + 1),
            date: nextDate.toDateString(),
            dayOfWeek: getDaysOfWeek(nextDate),
            predefinedSchedule: { id: 0, predefinedPlanCategories: [], scheduleName: "Rest Day" }
        });
    }

    return result;
}

function Draggable({ day, children }: { day: Day, children: React.ReactNode }) {
    const { attributes, listeners, setNodeRef, transform, over } = useDraggable({
        id: day.id
    });
    return (
        <button ref={setNodeRef} className={`${day.predefinedSchedule?.id === 0 ? "bg-green-200" : ""} border-[1px] border-solid rounded border-gray-400 bg-gray-100 px-2 py-3 min-w-[400px] ${over?.id === day.id ? "text-black" : ""}`}
            {...listeners} {...attributes}>
            {children}
        </button>
    );
}

function Droppable({ day }: { day: Day }) {
    const { isOver, setNodeRef } = useDroppable({
        id: day.id,
    });
    return (
        <div ref={setNodeRef} className={`bg-gray-50 flex gap-4 border-[1px] border-dotted rounded border-black px-3 py-4 ${isOver ? "border-green-600 bg-green-500 text-white" : ""}`}>
            <div>
                <p className='text-lg font-bold'>{day.dayOfWeek} </p>
                <p> {day.date}</p>
            </div>
            <Draggable day={day}>
                {day.predefinedSchedule?.scheduleName}
            </Draggable>
        </div>
    );
}

const CustomizeDropDownRoutine = ({ predefinedPlanSchedules, onChange }: { predefinedPlanSchedules: PredefinedPlanSchedule[], onChange: (predefinedPlanSchedules: UserPlanScheduleDto[]) => void }) => {
    const [sevenDays, setSevenDays] = useState<Day[]>([]);
    const [changed, setChanged] = useState(uuid());

    function handleDragEnd(event: DragEndEvent) {
        const { over, active } = event;
        setSevenDays((days) => {
            return days.map((day) => {
                if (day.id === over?.id) {
                    const newDay = days.find(day => day.id == active.id) as Day
                    return { ...day, predefinedSchedule: newDay.predefinedSchedule }
                } else if (day.id === active.id) {
                    const newDay = days.find(day => day.id == over?.id) as Day
                    if (newDay) {
                        return { ...day, predefinedSchedule: newDay.predefinedSchedule }
                    } else {
                        return day;
                    }
                }
                return day;
            })
        })
        setChanged(uuid());
    }

    useEffect(() => {
        if (sevenDays.length <= 0) return;
        const userPlanScheduleDto: UserPlanScheduleDto[] = []
        for (const day of sevenDays) {
            for (const category of day.predefinedSchedule?.predefinedPlanCategories || []) {
                userPlanScheduleDto.push({ dayorder: DaysofWeek[day.dayOfWeek], workoutCategoryId: category.id });
            }
            if (day.predefinedSchedule?.id == 0 && day.predefinedSchedule.predefinedPlanCategories.length == 0) {
                userPlanScheduleDto.push({ dayorder: DaysofWeek[day.dayOfWeek], workoutCategoryId: REST_CATEGORY_ID });
            }
        }
        onChange(userPlanScheduleDto);
    }, [changed, sevenDays])

    function handleDragStart(event: DragStartEvent) {
        const { active } = event;
        setDraggingDayId(active.id as number);
    }

    useEffect(() => {
        const days = getNext7Days();
        for (let i = 0; i < predefinedPlanSchedules.length; i++) {
            days[i].predefinedSchedule = predefinedPlanSchedules[i]
        }
        setSevenDays(days);
    }, [predefinedPlanSchedules])

    const [draggingDayId, setDraggingDayId] = useState<number | null>(null);
    const draggingDay = sevenDays.find(day => day.id == draggingDayId);
    return (
        <DndContext onDragEnd={handleDragEnd} onDragStart={handleDragStart}>
            <div className='flex flex-col gap-4'>
                {sevenDays.map((day) => (
                    <Droppable key={day.id} day={day} />
                ))}
            </div>
            {draggingDay ? <DragOverlay>
                <Draggable day={draggingDay}>
                    {draggingDay.predefinedSchedule?.scheduleName}
                </Draggable>
            </DragOverlay> : null}
        </DndContext>
    );
};

export default CustomizeDropDownRoutine;