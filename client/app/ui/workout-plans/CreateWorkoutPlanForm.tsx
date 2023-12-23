"use client"
import { Category, useAddWorkoutCategory } from '@/app/lib/api/workout-category';
import { uuid } from '@/app/lib/uuid';
import { Form, Formik, FormikErrors } from 'formik';
import { useState } from 'react';
import AddPredefinedWorkout from './AddPredefinedWorkout';


const Error = ({ error }: { error?: string | null }) => error ? <p className='bg-red-500 rounded-md p-2 text-white'>{error}</p> : null

const CreateWorkoutPlanForm = () => {
    const handleAddCard = () => {
        setWorkouts(workouts => [...workouts, { key: uuid(), categories: [] }])
    };
    const handleRemoveCard = (removekey: string) => {
        setWorkouts((workouts) => workouts.filter((c) => c.key !== removekey));
    };
    const [workouts, setWorkouts] = useState<Array<{ key: string, categories: Array<Category> }>>([]);
    const { post: addWorkoutCategory, loading, error, success, reset } = useAddWorkoutCategory()

    return (
        <div className="container mx-auto">
            {error ? <div className='p-4'>
                <Error error={error} />
            </div> : null}
            {success ?
                <div>
                    <div>Successfully create a plan!</div>
                    <button
                        className='text-white p-2 rounded-md min-w-[100px] bg-blue-500 mt-4'
                        onClick={() => reset()}
                    >Create Another Plan</button>
                </div>
                : <Formik
                    initialValues={{
                        name: "",
                        workoutPerCycle: 0,
                        repeatCycle: 0,
                        description: "",
                    }}
                    enableReinitialize
                    onSubmit={async (data, { resetForm, setSubmitting }) => {
                        try {
                            setSubmitting(true);
                            await addWorkoutCategory({
                                description: data.description,
                                name: data.name,
                                repeatCycle: data.repeatCycle,
                                workoutPerCycle: data.workoutPerCycle,
                                workoutSchedules: workouts.map((workout) => ([...workout.categories.map((category) => category.id)]))
                            });
                            resetForm();
                            setWorkouts([])
                        } catch (e) {
                            console.log(e);
                        }
                        setSubmitting(false);
                    }}
                    validate={(values) => {
                        const error: FormikErrors<typeof values> = {}
                        if (!values.name) error.name = "Please enter a name for the plan"
                        if (values?.repeatCycle <= 0) error.repeatCycle = "Repeat cycle must be greater than 0";
                        if (values.workoutPerCycle <= 0) error.workoutPerCycle = "Workout cycle must be greater than 0";
                        if (workouts.length == 0) error.description = "Workout must be greater than 0";
                        if (workouts.length != values.workoutPerCycle) error.description = "Workout repeat cycle must match length of workout";
                        return error;
                    }}
                >{(form) => <Form>
                    <legend>
                        <label>Name</label>
                        <input autoComplete='off' className='input' name="name" onChange={form.handleChange} value={form.values.name} />
                        <Error error={form.errors.name} />
                    </legend>
                    <legend className='mt-5'>
                        <label>Workout Per cycle</label>
                        <input autoComplete='off' className='input' name="workoutPerCycle" type='number' onChange={form.handleChange} value={form.values.workoutPerCycle} />
                        <Error error={form.errors.workoutPerCycle} />
                    </legend>

                    <legend className='mt-5'>
                        <label>Repeat Cycle</label>
                        <input autoComplete='off' className='input' name="repeatCycle" type='number' onChange={form.handleChange} value={form.values.repeatCycle} />
                        <Error error={form.errors.repeatCycle} />
                    </legend>

                    <legend className='mt-5'>
                        <label>Description</label>
                        <textarea autoComplete='off' className='input' name="description" rows={4} onChange={form.handleChange} value={form.values.description} />
                        <Error error={form.errors.description} />
                    </legend>
                    <legend className='mt-4'>
                        <h1 className="text-2xl mb-4">All Workouts</h1>
                        <label>click add workout to add a set of workout rules for each day in the workout cycle.</label>
                        <div className='mt-4'>
                            <button type='button' onClick={handleAddCard} className="bg-blue-500 text-white p-2 rounded-md mb-4">
                                Add Workout
                            </button>
                        </div>
                        <div className="flex flex-wrap">
                            {workouts.map((workout, key) => (
                                <AddPredefinedWorkout
                                    key={workout.key}
                                    index={workout.key}
                                    onRemove={handleRemoveCard}
                                    count={key + 1}
                                    onChange={(categories) => {
                                        setWorkouts((workouts) => ([...workouts.map((_workout) => ({ ..._workout, categories: _workout.key == workout.key ? categories : _workout.categories }))]))
                                    }} />
                            ))}
                        </div>
                        <button
                            className={`my-4  text-white p-2 px-10 rounded-md min-w-[100px] ${form.isSubmitting ? "bg-gray-500" : "bg-green-700"}`}
                            disabled={form.isSubmitting}
                            type="submit">{form.isSubmitting ? "Submitting please wait..." : "Submit"}</button>
                    </legend>
                </Form>}
                </Formik>}
        </div>
    );
};

export default CreateWorkoutPlanForm;