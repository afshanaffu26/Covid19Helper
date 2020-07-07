package com.example.covid19helper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SymptomAndTreatmentFragment extends Fragment {

    public SymptomAndTreatmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ExpandableListView expandableListView;
        View v = inflater.inflate(R.layout.fragment_symptom_and_treatment, container, false);
        String[] arrTitle = {
                "Symptoms","Prevention", "Treatment"
        };
        String[][] arrDescription = {
                {"COVID-19 affects different people in different ways. Most infected people will develop mild to moderate illness and recover without hospitalization.\n" +
                        "\n" +
                        "Most common symptoms:\n" +
                        "\n" +
                        "•\tfever\n" +
                        "•\tdry cough\n" +
                        "•\ttiredness\n" +
                        "\n" +
                        "Less common symptoms:\n" +
                        "\n" +
                        "•\taches and pains\n" +
                        "•\tsore throat\n" +
                        "•\tdiarrhoea\n" +
                        "•\tconjunctivitis\n" +
                        "•\theadache\n" +
                        "•\tloss of taste or smell\n" +
                        "•\ta rash on skin, or discolouration of fingers or toes\n" +
                        "\n" +
                        "Serious symptoms:\n" +
                        "\n" +
                        "•\tdifficulty breathing or shortness of breath\n" +
                        "•\tchest pain or pressure\n" +
                        "•\tloss of speech or movement\n" +
                        "\n" +
                        "Seek immediate medical attention if you have serious symptoms. Always call before visiting your doctor or health facility.\n" +
                        "\n" +
                        "People with mild symptoms who are otherwise healthy should manage their symptoms at home.\n" +
                        "\n" +
                        "On average it takes 5–6 days from when someone is infected with the virus for symptoms to show, however it can take up to 14 days.\n" +
                        "\n"},
                {"STAY HOME.\n" +
                        "SAVE LIVES.\n" +
                        "Help stop coronavirus\n" +
                        "1. STAY home as much as you can\n" +
                        "2. KEEP a safe distance\n" +
                        "3. WASH hands often\n" +
                        "4. COVER your cough\n" +
                        "5. SICK? call ahead\n"},
                {"To date, there are no specific vaccines or medicines for COVID-19. Treatments are under investigation, and will be tested through clinical trials.\n" +
                        "Self-care\n" +
                        "\n" +
                        "•\tIf you feel sick you should rest, drink plenty of fluid, and eat nutritious food. Stay in a separate room from other family members, and use a dedicated bathroom if possible. Clean and disinfect frequently touched surfaces.\n" +
                        "•\tEveryone should keep a healthy lifestyle at home. Maintain a healthy diet, sleep, stay active, and make social contact with loved ones through the phone or internet. Children need extra love and attention from adults during difficult times. Keep to regular routines and schedules as much as possible.\n" +
                        "•\tIt is normal to feel sad, stressed, or confused during a crisis. Talking to people you trust, such as friends and family, can help. If you feel overwhelmed, talk to a health worker or counsellor.\n" +
                        "\n" +
                        "Medical treatments\n" +
                        "\n" +
                        "•\tIf you have mild symptoms and are otherwise healthy, self-isolate and contact your medical provider or a COVID-19 information line for advice.\n" +
                        "•\tSeek medical care if you have a fever, a cough, and difficulty breathing. Call in advance.\n" +
                        "\n"},
        };
        expandableListView = v.findViewById(R.id.expandable_list_view);
        ExpandableTextViewAdapter adapter = new ExpandableTextViewAdapter(arrTitle,arrDescription,v.getContext());
        expandableListView.setAdapter(adapter);
        return v;
    }
}
