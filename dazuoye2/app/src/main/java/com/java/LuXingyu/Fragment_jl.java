package com.java.LuXingyu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Fragment_jl extends Fragment implements OnTouchUpListener{

    private static Fragment_jl f_jl;
    private PullLoadRecyclerViewLayout mLayout;
    private List<Introduction> newsMyList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private View rootView;
    private View mHeaderView;
    private View mFooterView;
    int jl;
    Button button1,button2,button3;
    Integer[] Cluster_one = {3,6,24,37,46,48,55,64,68,70,73,83,93,95,100,110,130,137,
            141,143,147,149,152,153,162,164,167,177,191,192,196,198,203,209,210,217,
            218,226,228,234,238,239,252,257,269,271,273,278,279,284,287,293,299,300,
            305,309,313,316,323,365,376,377,419,422,426,427,428,431,434,437,473,484,520,526,531,534,
            547,551,552,556,584,592,608,611,623,626,627,635,642,648,658,659,673,691,692,693,695};
    Integer[] Cluster_two = {5, 9, 12, 13, 19, 20, 23, 25, 26, 30, 32, 34, 35, 36, 41, 42, 45, 51, 54, 57, 58, 61, 63, 69, 71, 75, 78,
            80, 82, 86, 91, 92, 96, 98, 99, 101, 102, 103, 104, 107, 108, 109, 111, 112, 113, 115, 116, 118, 119, 120, 123, 124, 125, 128,
            134, 136, 138, 139, 142, 146, 151, 154, 155, 157, 158, 159, 163, 165, 166, 170, 172, 173, 174, 176, 181, 182, 183, 184, 185,
            186, 188, 193, 194, 195, 197, 199, 201, 204, 212, 213, 216, 223, 229, 230, 231, 232, 236, 237, 241, 242, 243, 244,
            245, 246, 247, 249, 254, 255, 256, 258, 262, 263, 264, 266, 267, 268, 272, 277, 280, 281, 282, 285, 286, 288, 289, 290,
            291, 292, 294, 295, 296, 298, 301, 302, 306, 308, 310, 311, 312, 314, 317, 318, 319, 320, 322, 327, 329, 332, 333, 334,
            335, 338, 339, 341, 342, 343, 344, 345, 346, 347, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362,
            363, 364, 366, 369, 370, 371, 372, 373, 374, 375, 378, 379, 380, 381, 382, 383, 385, 386, 387, 388, 389, 390, 391, 392,
            393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 406, 407, 408, 409, 410, 412, 413, 415, 418, 421, 425, 432,
            433, 436, 438, 440, 442, 443, 444, 445, 446, 447, 448, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463,
            464, 465, 466, 467, 468, 469, 470, 471, 472, 474, 475, 476, 477, 478, 479, 480, 481, 482, 485, 486, 487, 488, 489, 491,
            492, 494, 495, 496, 497, 498, 500, 501, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 515, 517, 519, 521, 523, 524,
            525, 527, 532, 533, 536, 537, 538, 540, 541, 542, 548, 549, 550, 553, 554, 555, 557, 558, 559, 560, 561, 562, 563, 564,
            565, 566, 567, 568, 569, 570, 571, 573, 574, 575, 576, 577, 578, 579, 580, 582, 583, 586, 587, 589, 590,
            593, 595, 596, 600, 601, 602, 603, 604, 605, 606, 609, 610, 612, 613, 615, 616, 617, 618, 622, 631, 632, 633, 636, 637,
            639, 640, 643, 646, 652, 654, 655, 656, 657, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 675, 676, 677, 678, 679,
            680, 684, 685, 686, 687, 688, 689, 694, 696, 697};
    Integer[] Cluster_three = {0, 1, 2, 4, 7, 8, 10, 11, 14, 15, 16, 17, 18, 21, 22, 27, 28, 29, 31, 33, 38, 39, 40, 43, 44, 47,
            49, 50, 52, 53, 56, 59, 60, 62, 65, 66, 67, 72, 74, 76, 77, 79, 81, 84, 85, 87, 88, 89, 90, 94, 97, 105, 106, 114, 117,
            121, 122, 126, 127, 129, 131, 132, 133, 135, 140, 144, 145, 148, 150, 156, 160, 161, 168, 169, 171, 175, 178, 179, 180,
            187, 189, 190, 200, 202, 205, 206, 207, 208, 211, 214, 215, 219, 220, 221, 222, 224, 225, 227, 233, 235, 240, 248, 250,
            251, 253, 259, 260, 261, 265, 270, 274, 275, 276, 283, 297, 303, 304, 307, 315, 321, 324, 325, 326, 328, 330, 331, 336,
            337, 340, 348, 367, 368, 384, 405, 411, 414, 416, 417, 420, 423, 424, 429, 430, 435, 439, 441, 449, 450, 483, 490, 493,
            499, 502, 503, 514, 516, 518, 522, 528, 529, 530, 535, 539, 543, 544, 545, 546, 572, 581, 585, 588, 591, 594, 597, 598,
            599, 607, 614, 619, 620, 621, 624, 625, 628, 629, 630, 634, 638, 641, 644, 645, 647, 649, 650, 651, 653, 660, 661, 662,
            674, 681, 682, 683, 690, 698};
    News n[];

    public int load_time;

    public Fragment_jl() {
        // Required empty public constructor
        jl=0;
    }

    public static Fragment_jl Instance() {
        if(f_jl==null) {
            f_jl= new Fragment_jl();
        }
        return f_jl;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNews();
        load_time=0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        newsAdapter=new NewsAdapter(newsMyList);
        rootView=inflater.inflate(R.layout.fragment_jl, container, false);
        LayoutInflater my_inflater = LayoutInflater.from(getActivity());
        mHeaderView = my_inflater.inflate(R.layout.co_refresh_header, null);
        mFooterView = my_inflater.inflate(R.layout.co_refresh_footer, null);
        mLayout = (PullLoadRecyclerViewLayout)rootView.findViewById(R.id.pull2);
        mLayout.addFooterView(mFooterView, DisplayUtil.dpToPx(getActivity(), 40));
        mLayout.addHeaderView(mHeaderView, DisplayUtil.dpToPx(getActivity(), 60));
        mLayout.setMyRecyclerView(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false),
                newsAdapter, true);
        mLayout.addOnTouchUpListener(this);

        NewsManager newsManager = AppManager.getNewsManager();
        n=newsManager.getOneTypeNews("event");



        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ;
            }
        });
        button1=(Button)rootView.findViewById(R.id.button_jl_1);
        button2=(Button)rootView.findViewById(R.id.button_jl_2);
        button3=(Button)rootView.findViewById(R.id.button_jl_3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jl=1;
                initNews();
                newsAdapter.notifyDataSetChanged();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jl=2;
                initNews();
                newsAdapter.notifyDataSetChanged();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               jl=3;
               initNews();
                newsAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }


    private void initNews(){
        newsMyList.clear();
        if(Connect.networkConnected(getActivity())) {
            int num=0;
                for (int i = 0; i < 20; i++) {
                    if(jl==1)
                        newsMyList.add(new Introduction(n[Cluster_three[i]]));
                    else if(jl==2)
                        newsMyList.add(new Introduction(n[Cluster_two[i]]));
                    else if(jl==3)
                        newsMyList.add(new Introduction(n[Cluster_one[i]]));
                }
        }

    }

    private void onDataRefreshing() {
        onRefreshFinish(true);
    }

    private void onDataLoadingMore() {
        if(Connect.networkConnected(getActivity())) {
            //增加新闻
            load_time++;
            for (int i = 0; i < 20; i++){
                if(load_time*20+i>698)
                    return;
                if(jl==1)
                    newsMyList.add(new Introduction(n[Cluster_three[20*load_time+i]]));
                else if(jl==2)
                    newsMyList.add(new Introduction(n[Cluster_two[20*load_time+i]]));
                else if(jl==3)
                    newsMyList.add(new Introduction(n[Cluster_one[20*load_time+i]]));
            }
            //更新adapter
            newsAdapter.notifyDataSetChanged();
            //更新之后滑到最底端
            //mLayout.setRecyclerViewScrollToPosition(newsAdapter.getItemCount()-1);
            //停止更新动作
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onLoadMoreFinish(true);
                }
            }, 300);
        }
    }

    @Override
    public void OnRefreshing() {
        mLayout.setHeaderState(PullLoadRecyclerViewLayout.HEADER_STATE_REFRESHING);
        onDataRefreshing();
    }

    @Override
    public void OnLoading() {
        mLayout.setFooterState(PullLoadRecyclerViewLayout.FOOTER_STATE_LOADING);
        onDataLoadingMore();
    }

    private void onRefreshFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setHeaderState(success ? PullLoadRecyclerViewLayout.HEADER_STATE_COMPLETE :
                        PullLoadRecyclerViewLayout.HEADER_STATE_FAIL);
            }
        }.sendEmptyMessageDelayed(0, 100);
    }

    private void onLoadMoreFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setFooterState(success ? PullLoadRecyclerViewLayout.FOOTER_STATE_COMPLETE :
                        PullLoadRecyclerViewLayout.FOOTER_STATE_FAIL);
            }
        }.sendEmptyMessageDelayed(0, 100);
    }


    @Override
    public void onStart() {
        newsAdapter.notifyDataSetChanged();
        super.onStart();
    }

}