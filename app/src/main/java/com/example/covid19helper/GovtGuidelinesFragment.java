package com.example.covid19helper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GovtGuidelinesFragment extends Fragment {

    public GovtGuidelinesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ExpandableListView expandableListView;
        View v = inflater.inflate(R.layout.fragment_govt_guidelines, container, false);
        String[] arrTitle = {
                "For Induviduals", "For Workplaces","For communities","For childcare facilities"
        };
        String[][] arrDescription = {
                {"Canadians should continue to think ahead about the actions that they can take to stay healthy and prevent the spread of any illness, especially respiratory infections.\n" +
                        "\n" +
                        "Now and always during cold and flu season, stay home if you are sick. Encourage those you know are sick to stay home until they no longer have symptoms.\n" +
                        "\n" +
                        "Since respiratory viruses, such as the one that causes COVID-19, are spread through contact, change how you greet one another. Instead of a handshake, a kiss or a hug, a friendly wave or elbow bump is less likely to expose you to respiratory viruses.\n" +
                        "\n" +
                        "Practise frequent hygiene, which includes proper hand washing and coughing and sneezing etiquette. Clean and disinfect frequently touched objects and surfaces, such as toys and door handles.\n" +
                        "\n" +
                        "These are the most important ways that you can protect yourself and your family from respiratory illness, including COVID-19."},
                {"Across Canada, we are changing the way we work to prevent the spread of COVID-19. For some workplaces, this may mean changing hours of operation, closing for a period of time or working from home. Others continue to go to work because their jobs are essential to keeping Canada functioning during this outbreak.\n" +
                        "Essential workers are considered critical to preserving life, health and basic societal functioning. This includes, but is not limited to:\n" +
                        "•\tfirst responders\n" +
                        "•\thealth care workers\n" +
                        "•\thydro and natural gas\n" +
                        "•\tcritical infrastructure workers\n" +
                        "•\tworkers who are essential for supplying society with critical goods such as food and medicines\n" +
                        "All employees should understand and comply with the infection prevention policies and practices in place in their workplaces. Employers should use the risk-informed decision-making guidelines for workplaces and businesses during the COVID-19 pandemic.\n" +
                        "Employers and employees will need to work together to protect their own health and their clients' health, as well as deliver essential services.\n" +
                        "\n"},
                {"Physical distancing measures are a way to reduce COVID-19 transmission in the community by minimizing close contact with others, especially people who are at high risk for severe illness during the peak of the outbreak.\n" +
                        "Some of the physical distancing measures need extensive preparation. Community planners should prepare for:\n" +
                        "•\tinterruptions in social supports\n" +
                        "•\treduction in public services like transit and access to community centres\n" +
                        "•\tfinancial consequences from the reduction of services or cancelled events\n" +
                        "Planners, administrators and employers must work together to put into effect community-based measures to protect:\n" +
                        "•\tgroups\n" +
                        "•\temployees\n" +
                        "•\tthe general population\n" +
                        "\n"},
                {"To help reduce the transmission of COVID-19, there have been widespread closures of daycares and schools. Some childcare services, especially those providing programs for children of essential workers, are open and making changes to keep staff and families safe.\n\n" +
                        "Childcare providers, parents and attending children should be well informed about the ongoing situation and the protective measures being put in place, in age- appropriate language.\n" +
                        "For childcare centres that remain open to support essential workers, the following should be considered. Please keep in mind that situations may vary and these guidelines should be adapted for:\n" +
                        "•\tthe different needs of girls, boys, women, men and gender diverse children and adults\n" +
                        "•\tother cultural, linguistic and social contexts\n" +
                        "•\tthe resources that are available to each childcare facility\n" +
                        "Many of the children who attend childcare centres are very young. Given the context of school closures across the country, the age of those in attendance may vary. There are potential challenges with the ability of younger children to carry out preventative measures (such as physical distancing). Older children may help to reinforce preventative measures among younger children. Older children in this context may require more psychosocial and behavioural support in response to the unique stresses of COVID-19.\n" +
                        "Childcare centres should work closely with their public health authorities and partners to consider strategies, such as:\n" +
                        "•\texplaining in age-appropriate language the situation to the children and demonstrating behaviours they can adopt to protect themselves and those around them\n" +
                        "•\treinforcing and reminding children to:\n" +
                        "o\t\"keep hands to yourself\"\n" +
                        "o\t\"not put toys or other objects in their mouths\"\n" +
                        "•\ttaking children outside often (play time, snacks, crafts)\n" +
                        "•\tcreating and playing games that encourage physical distancing\n" +
                        "o\tfor example, with music, have children spread their arms side to side and spin around slowly trying not to touch their friends\n" +
                        "•\tsetting up play stations with limited numbers of children at each station\n" +
                        "•\tconsidering the use of educational videos and online programs where children can sit independently\n" +
                        "o\tministries of education may have age-appropriate learning resources as well as resources that consider and reflect gender diversity\n" +
                        "\n"}
        };
        expandableListView = v.findViewById(R.id.expandable_list_view);
        ExpandableTextViewAdapter adapter = new ExpandableTextViewAdapter(arrTitle,arrDescription,v.getContext());
        expandableListView.setAdapter(adapter);
        return v;
    }
}
