"use client"
import { useMyActivePlan } from "./lib/api/workout-routine";
import { getDaysOfWeek, DaysofWeek } from "./lib/util";

export default function Page() {
  const { data: myActivePlan } = useMyActivePlan();
  const today = getDaysOfWeek(new Date());
  const todaysWorkouts = myActivePlan?.data.plan.categorySchedules?.filter((plan) => plan.dayOrder === DaysofWeek[today]);

  const workouts = todaysWorkouts?.flatMap((workout) => workout.workoutCategory.category).join("/")
  return (
    <section className='w-full flex-center flex-col'>
      {todaysWorkouts?.length ? <div className="flex flex-col">
        <p className='desc text-center '> Today is your</p>
        <h1 className='mt-20 text-[40px] text-center head_text'>
          {/* <br className='max-md:hidden' /> */}
          <span className='orange_gradient text-center'>{workouts} day</span>
        </h1>
        <button className="mt-20 text-gray-900 bg-gradient-to-r from-teal-200 to-lime-200 hover:bg-gradient-to-l hover:from-teal-200 hover:to-lime-200 focus:ring-4 focus:outline-none focus:ring-lime-200 dark:focus:ring-teal-700 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">View My Schedule</button>
        <button className="mt-20 text-gray-900 bg-gradient-to-r from-red-200 via-red-300 to-yellow-200 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-red-100 dark:focus:ring-red-400 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">My History History</button>
      </div> : null}
      {/* <p className='desc text-center'>
        GymSajit is the platform where you will keep track of all your gym progress.
      </p>

      <div className='mt-10 text-center '>
        <p className='desc text-center'>
          Today is your
        </p>

      </div>
      <h1 className='orange_gradient text-center head_text'> Upper body day</h1> */}
    </section>
  );
}
